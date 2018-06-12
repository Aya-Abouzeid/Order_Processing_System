package Library;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.HashMap;

import com.lowagie.text.pdf.codec.Base64.*;

import groovyjarjarantlr.debug.InputBufferEventSupport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Report {

	Connection con;
	Statement stmt;
	ResultSet rs;
	Database db = new Database();

	public static void main(String[] args) {

		System.out.println( System.getProperty("user.dir"));
		Report report = new Report();
		report.getTotalSales();
//		report.getBestSellingBooks();
//		report.getTopCustomers();
	}

	public void getTopCustomers() {

		String reportSource =  System.getProperty("user.dir") +"/src/report/top.jrxml";
		String reportDest = "/home/youssef/Desktop/Order_Processing_System/out.html";

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			Connection con;

			Class.forName("com.mysql.jdbc.Driver");
			con=db.getCon();

			JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
			JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

			JasperViewer.viewReport(jasperPrint, false);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void getTotalSales() {
		String reportSource =  "/home/youssef/Desktop/Order_Processing_System/src/report/top2.jrxml";
		String reportDest = "/home/youssef/Desktop/Order_Processing_System/total_sales_out.html";

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			Connection con;

			Class.forName("com.mysql.jdbc.Driver");
			con=db.getCon();
			JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
			JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

			JasperViewer.viewReport(jasperPrint, false);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void getBestSellingBooks() {
		String reportSource =  "/home/youssef/Desktop/Order_Processing_System/src/report/bestSellingBooks.jrxml";
		String reportDest = "/home/youssef/Desktop/Order_Processing_System/best_selling_out.html";

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			Connection con;

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("passssssssssssssssssssssss");

			con=db.getCon();
			JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
			JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

			JasperViewer.viewReport(jasperPrint, false);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}