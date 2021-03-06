package br.com.sematec.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object executaTX(InvocationContext context) throws Exception {
		System.out.println("executaTX");
		manager.getTransaction().begin();
		Object object = context.proceed();
		manager.getTransaction().commit();
		return object;
	}
}
