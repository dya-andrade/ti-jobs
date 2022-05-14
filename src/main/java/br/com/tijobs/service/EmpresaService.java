package br.com.tijobs.service;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public void cadastrarEmpresa(Empresa empresa) throws IOException {
		empresaRepository.save(empresa);

		FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/empresa.xhtml");
	}

}
