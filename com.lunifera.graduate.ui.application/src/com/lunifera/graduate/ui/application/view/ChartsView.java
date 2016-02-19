/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import com.lunifera.graduate.ui.application.view.api.IConstants;
import com.lunifera.graduate.ui.fields.OSGiBeanFieldGroup;
import com.lunifera.graduate.ui.util.Util;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class ChartsView {

	@Inject
	private VerticalLayout parent;
	@Inject
	private IEventBroker eventBroker;
	@Inject
	private MPart mPart;

	private Chart chart;
	private EventHandler inputHandler;
	private OSGiBeanFieldGroup<?> fieldGroup;
	private DataSeries series;

	@PostConstruct
	protected void setup() {
		chart = new Chart(ChartType.PIE);

		Configuration conf = chart.getConfiguration();

		conf.setTitle("Exam results");

		PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setCursor(Cursor.POINTER);
		DataLabels dataLabels = new DataLabels(true);
		dataLabels.setFormatter("''+ this.point.name +': '+ this.y +' exams'");
		plotOptions.setDataLabels(dataLabels);

		plotOptions.setDepth(45);
		conf.setPlotOptions(plotOptions);

		setSeries(null);

		Options3d options3d = new Options3d();
		options3d.setEnabled(true);
		options3d.setAlpha(60);
		conf.getChart().setOptions3d(options3d);

		chart.addPointClickListener(new PointClickListener() {
			@Override
			public void onClick(PointClickEvent event) {
				Notification.show("Click: "
						+ series.get(event.getPointIndex()).getName());
			}
		});

		chart.drawChart(conf);
		parent.addComponent(chart);

		inputHandler = new EventHandler() {
			@Override
			public void handleEvent(Event event) {
				repaint(event.getProperty(IEventBroker.DATA));
			}
		};

		eventBroker.subscribe(IConstants.TOPIC__SELECTION_ALL, inputHandler);
	}

	@PreDestroy
	public void dispose() {
		eventBroker.unsubscribe(inputHandler);
		fieldGroup.dispose();
	}

	private DataSeries setSeries(Object entity) {
		Configuration conf = chart.getConfiguration();

		series = new DataSeries();
		DataSeriesItem chrome = new DataSeriesItem("90%-100%",
				(int) (Math.random() * 20));
		chrome.setSliced(true);
		chrome.setSelected(true);
		series.add(chrome);
		series.add(new DataSeriesItem("80%-90%", (int) (Math.random() * 30)));
		series.add(new DataSeriesItem("70%-80%", (int) (Math.random() * 50)));
		series.add(new DataSeriesItem("60%-70%", (int) (Math.random() * 20)));
		series.add(new DataSeriesItem("50%-60%", (int) (Math.random() * 10)));
		series.add(new DataSeriesItem("0%-50%", (int) (Math.random() * 10)));
		conf.setSeries(series);

		chart.drawChart(conf);
		
		return series;
	}

	protected void repaint(Object entity) {
		parent.getUI().accessSynchronously(new Runnable() {
			public void run() {
				setSeries(entity);
			}
		});
	}

	public String getDescription() {
		return "3D Pie chart";
	}

}
