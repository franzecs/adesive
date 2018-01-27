package com.ikytus.fp.util.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ikytus.fp.model.AbstractEntity;
import com.ikytus.fp.service.AbstractService;

@Service
public class GravarImagem {

	@Autowired 
	private ServletContext context;
	
	public String imagemString;
	
	public Path path;
	
	public static String encodeImagem(byte[] imageByteArray) {
        return Base64.encodeBase64String(imageByteArray);
    }
			
	public String gravarImagemBase64(MultipartFile file){
		try{
			imagemString = encodeImagem(file.getBytes());
		}catch (IOException e) {
			e.printStackTrace();
		}
		return imagemString;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String gravaImagemBase64Service(MultipartFile file, AbstractService service, AbstractEntity entidade){
		
		if(gravarImagemBase64(file).isEmpty()) {
			service.salvar(entidade);
		}else {
			imagemString = "data:image/png;base64," + imagemString;
			service.salvar(entidade,imagemString);
		}
		return gravarImagemBase64(file);
	}

	public String gravarImagemArquivo(MultipartFile file, String pasta) {
		
		String nomeImagem = file.getOriginalFilename();
		
		if(nomeImagem.isEmpty()){
			return nomeImagem;
		}else{
			try {
				byte[] bytes = file.getBytes();
				path = Paths.get(context.getRealPath("/img/") + pasta +"/" + nomeImagem);
				Files.write(path, bytes);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			return nomeImagem;
		}
	}
	
	public String recuperarImagemArquivo(String pasta, String arquivo) throws IOException{
		
		path = Paths.get(context.getRealPath("/img/")+ pasta);
		
		File file = new File(path + "/" + arquivo);
        
		// Reading a Image file from file system
        FileInputStream imageInFile = new FileInputStream(file);
        byte imageData[] = new byte[(int) file.length()];
        imageInFile.read(imageData);
        imageInFile.close();
        
        // Converting Image byte array into Base64 String
        imagemString = "data:image/png;base64," + encodeImagem(imageData);
		
        return imagemString;
	}
	
	public void criarPasta(String pasta){
		path = Paths.get(context.getRealPath("/"));
		File file = new File(path+ File.separator+pasta);
		file.mkdirs();
	}
	
}
