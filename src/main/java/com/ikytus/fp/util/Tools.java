package com.ikytus.fp.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.util.security.GrupoRepository;
import com.ikytus.fp.util.security.model.Grupo;
import com.ikytus.fp.util.security.model.Usuario;
import com.ikytus.fp.util.security.model.UsuarioSistema;

@Component
public class Tools {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	
	public Tools() {
		
	}
	
	public Usuario getUsuario(){
			Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication(); 
			UsuarioSistema user = (UsuarioSistema) authentication.getPrincipal();
			return user.getUsuario();
	}
	
	public Grupo getGrupo() {
		
		Grupo grupo = new Grupo();
		List<Grupo> grupos = getUsuario().getGrupos();
		
		for(Grupo g: grupos) {
			grupo = g;			
		}
		
		return grupo; 
	}
	
	public Grupo getGrupoMaster() {
		return grupoRepository.findByNome("ROLE_MASTERROOT");
	}
	
	public Grupo getGrupoAdmLoc() {
		return grupoRepository.findByNome("ROLE_ADMLOC");
	}
	
	public Grupo getGrupoClientes() {
		return grupoRepository.findByNome("ROLE_CLIENTES");
	}
	
	public Empresa getEmpresa(){
		return  getUsuario().getEmpresa();
	}
	
	public Empresa getFornecedor(){
		return  getEmpresa().getFornecedor();
	}
	
	public void getCSV(Part arquivo) throws IOException {
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		scanner.useDelimiter(";");

		while(scanner.hasNext()) {
			String linha = scanner.nextLine();
			if(linha !=null && !linha.trim().isEmpty()) {
				linha = linha.replace("\"", "");
				String[] dados = linha.split("\\;");
				System.out.println("nome: " + dados[0] + " e o email: " + dados[1]);
			}
		}
		scanner.close();
	}
	
	@Bean
	public String getIp(String ip) throws UnknownHostException, MalformedURLException {
		
		InetAddress address = InetAddress.getByName(new URL(ip).getHost());
		return address.getHostAddress();
	}
		
}
