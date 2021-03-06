package br.com.gerenciador.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gerenciador.modelo.Banco;
import br.com.gerenciador.modelo.Empresa;

public class NovaEmpresa implements Action {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

		System.out.println("Cadastrando nova empresa");

		String nomeEmpresa = req.getParameter("nome");
		String dataCriacao = req.getParameter("data");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateParse = null;
		try {
			dateParse = sdf.parse(dataCriacao);
		} catch (ParseException e) {
			throw new ServletException("Data n�o conseguiu ser parseada", e);
		}

		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		empresa.setDataCriacao(dateParse);

		Banco banco = new Banco();
		banco.adiciona(empresa);

		req.setAttribute("empresa", empresa);
		return "redirect:ListaEmpresa";
	}

}
