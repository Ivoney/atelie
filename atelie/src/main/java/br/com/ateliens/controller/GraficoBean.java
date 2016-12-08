package br.com.ateliens.controller;

import br.com.ateliens.model.Venda;
import br.com.ateliens.repository.VendaRepository;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;

@Named
@ViewScoped
public class GraficoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private BarChartModel barModel;

    private LineChartModel areaModel;

    public BarChartModel getBarModel() {
        return barModel;
    }

    public LineChartModel getAreaModel() {
        return areaModel;
    }

    @Inject
    private VendaRepository vendasRepository;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    private BarChartModel animatedModelBarra;

    public BarChartModel getAnimatedModelBarra() {
        return animatedModelBarra;
    }

    private void createAnimatedModels() {

        animatedModelBarra = initBarModel();
        animatedModelBarra.setTitle("Gráfico de barras tipos de vendas");

        animatedModelBarra.setShowPointLabels(true);
        animatedModelBarra.setAnimate(true);
        animatedModelBarra.setLegendPosition("ne");

        Axis yAxis = animatedModelBarra.getAxis(AxisType.Y);
        yAxis.setLabel("Tipos de Vendas");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }

    private BarChartModel initBarModel() {

        BarChartModel model = new BarChartModel();

        
        ChartSeries ccredito = new ChartSeries();
        ccredito.setLabel("Cartão de Crédito");
        ccredito.set("2012", 400);
        ccredito.set("2013", 1550);
        ccredito.set("2014", 1450);
        ccredito.set("2015", 1300);
        ccredito.set("2016", 500);

        ChartSeries cdebito = new ChartSeries();
        cdebito.setLabel("Cartão de Débito");
        cdebito.set("2012", 800);
        cdebito.set("2013", 940);
        cdebito.set("2014", 1750);
        cdebito.set("2015", 1290);
        cdebito.set("2016", 1000);

        ChartSeries dinheiro = new ChartSeries();
        dinheiro.setLabel("Dinheiro");
        dinheiro.set("2012", 1200);
        dinheiro.set("2013", 1000);
        dinheiro.set("2014", 1080);
        dinheiro.set("2015", 1250);
        dinheiro.set("2016", 1100);

        ChartSeries cheque = new ChartSeries();
        cheque.setLabel("Cheque");
        cheque.set("2012", 800);
        cheque.set("2013", 1005);
        cheque.set("2014", 1000);
        cheque.set("2015", 1100);
        cheque.set("2016", 1200);

        model.addSeries(ccredito);
        model.addSeries(cdebito);
        model.addSeries(dinheiro);
        model.addSeries(cheque);
        return model;

    }

    private void createBarModels() {
        createBarModel();

    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Gráficos por tipos de pagamentos");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Vendas por tipo");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade de Vendas");
        yAxis.setMin(0);
        yAxis.setMax(2000);
    }

}
