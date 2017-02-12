/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Usuario;
import com.oliveira.pedidovenda.repository.Pedidos;
import com.oliveira.pedidovenda.security.UsuarioLogado;
import com.oliveira.pedidovenda.security.UsuarioSistema;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Adriano
 */
@Named
@RequestScoped
public class GraficoPedidosCriadosBean {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private LineChartModel model;

	public void preRender() {
		this.model = new LineChartModel();
		this.model.setTitle("Pedidos criados");
		this.model.setLegendPosition("e");
		this.model.setAnimate(true);
		
		this.model.getAxes().put(AxisType.X, new CategoryAxis());
		
		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
	}
	
	private void adicionarSerie(String rotulo, Usuario criadoPor) {
		Map<Date, BigDecimal> valoresPorData = this.pedidos.valoresTotaisPorData(15, criadoPor);
		
		ChartSeries series = new ChartSeries(rotulo);
		
                valoresPorData.keySet().stream().forEach((data) -> {
                    series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
            });
		
		this.model.addSeries(series);
	}

	public LineChartModel getModel() {
		return model;
	}
	
}
