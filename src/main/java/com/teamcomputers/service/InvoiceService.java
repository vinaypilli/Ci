package com.teamcomputers.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.Order;
import com.teamcomputers.model.OrderDetails;


@Service
public class InvoiceService {

	public byte[] generateInvoice(Order invoiceData) {
		try {
			// Create a new Word document
			XWPFDocument document = new XWPFDocument();

			// Create the header

			XWPFParagraph headerParagraph = document.createParagraph();
			XWPFRun headerRun = headerParagraph.createRun();
			String line = "========================================";
			int lineSize = 40; // Adjust the line size according to your requirement
			String headerText = "                                   INVOICE";
			headerRun.setText(line);
			headerRun.addBreak();
			headerRun.setText(headerText);
			headerRun.addBreak();
			headerRun.setText(line.substring(0, lineSize));

			// Create the order details table
			XWPFTable orderDetailsTable = document.createTable(invoiceData.getOrderDetails().size() + 2, 4);
			XWPFTableRow headerRow = orderDetailsTable.getRow(0);
			headerRow.getCell(0).setText("Product Name");
			headerRow.getCell(1).setText("Price");
			headerRow.getCell(2).setText("Quantity");
			headerRow.getCell(3).setText("Total");

			int rowIndex = 1;
			for (OrderDetails orderDetail : invoiceData.getOrderDetails()) {
				XWPFTableRow row = orderDetailsTable.getRow(rowIndex);
				row.getCell(0).setText(orderDetail.getProductName());
				row.getCell(1).setText(String.valueOf(orderDetail.getPrice()));
				String quantity = orderDetail.getQuantity() + "(" + orderDetail.getUnitofmeasure()+ ")";
				row.getCell(2).setText(quantity);
				row.getCell(3).setText(String.valueOf(orderDetail.getTotal()));
				rowIndex++;
			}

			// Create the summary section
			XWPFParagraph summaryParagraph = document.createParagraph();
			summaryParagraph.setAlignment(ParagraphAlignment.LEFT);
			summaryParagraph.setSpacingBefore(10);
			summaryParagraph.setSpacingAfter(10);
			summaryParagraph.createRun().setText("SUBTOTAL: " + invoiceData.getSubtotal());
			summaryParagraph.createRun().addBreak();
			summaryParagraph.createRun().setText("CGST Tax: " + invoiceData.getCgst());
			summaryParagraph.createRun().addBreak();
			summaryParagraph.createRun().setText("SGST Tax: " + invoiceData.getSgst());
			summaryParagraph.createRun().addBreak();
			summaryParagraph.createRun().setText("Service Tax: " + invoiceData.getService());
			summaryParagraph.createRun().addBreak();
			summaryParagraph.createRun().setText("Total Tax: " + invoiceData.getTotaltax());
			summaryParagraph.createRun().addBreak();
			summaryParagraph.createRun().setText("Discount: " + invoiceData.getTotaltax());
			summaryParagraph.createRun().addBreak();
			summaryParagraph.createRun().setText("GRAND TOTAL: " + invoiceData.getGrand());

			// Create the customer details section
			XWPFParagraph customerParagraph = document.createParagraph();
			customerParagraph.setAlignment(ParagraphAlignment.LEFT);
			customerParagraph.setSpacingBefore(10);
			customerParagraph.setSpacingAfter(10);
			customerParagraph.createRun().setText("Customer Mobile: " + invoiceData.getCustomer().getContact());
			customerParagraph.createRun().addBreak();
			customerParagraph.createRun().setText("Payment Type: " + invoiceData.getPaymentType());

			// Create the footer

			XWPFParagraph footerParagraph = document.createParagraph();
			XWPFRun footerRun = footerParagraph.createRun();
			footerRun.setText(line.substring(0, lineSize));
			footerRun.addBreak();
			footerRun.setText("           THANK YOU FOR YOUR PURCHASE");
			footerRun.addBreak();
			footerRun.setText(line.substring(0, lineSize));

			// Generate the DOC file as a byte array
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			document.write(outputStream);
			document.close();
			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to generate invoice.");
		}
	}

//    public byte[] generateInvoice(Order invoiceData) {
//        try {
//            // Create a new Word document
//            XWPFDocument document = new XWPFDocument();
//
//            // Create the invoice text using the provided invoice data
//            String invoiceText = "Invoice\n\n";
//            
//            // Add order details
//            invoiceText += "Order Details:\n";
//            for (OrderDetails orderDetail : invoiceData.getOrderDetails()) {
//                invoiceText += "Product Name: " + orderDetail.getProductName() + "\n";
//                invoiceText += "Rate MRP: " + orderDetail.getRatemrp() + "\n";
//                invoiceText += "Quantity: " + orderDetail.getQantity() + "\n";
//                invoiceText += "Amount: " + orderDetail.getAmount() + "\n\n";
//            }
//            
//            invoiceText += "Customer Mobile: " + invoiceData.getCustomer().getContact() + "\n";
//            invoiceText += "Created By: " + invoiceData.getCreatedBy() + "\n";
//            invoiceText += "Updated By: " + invoiceData.getUpdatedBy() + "\n";
//            invoiceText += "Created Date: " + invoiceData.getCreatedDate() + "\n";
//            invoiceText += "Updated Date: " + invoiceData.getUpdatedDate() + "\n";
//            invoiceText += "Discount: " + invoiceData.getDiscount() + "\n";
//            invoiceText += "Subtotal: " + invoiceData.getSubtotal() + "\n";
//            invoiceText += "CGST: " + invoiceData.getCgst() + "\n";
//            invoiceText += "SGST: " + invoiceData.getSgst() + "\n";
//            invoiceText += "Service: " + invoiceData.getService() + "\n";
//            invoiceText += "Grand: " + invoiceData.getGrand() + "\n";
//            invoiceText += "Total Amount: " + invoiceData.getTotalAmount() + "\n";
//            invoiceText += "Payment Type: " + invoiceData.getPaymentType() + "\n";
//            invoiceText += "Status: " + invoiceData.isStatus() + "\n";
//
//            // Add the invoice text to the document
//            XWPFParagraph paragraph = document.createParagraph();
//            XWPFRun run = paragraph.createRun();
//            run.setText(invoiceText);
//
//            // Generate the DOC file as a byte array
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            document.write(outputStream);
//            document.close();
//            return outputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to generate invoice.");
//        }
//    }

	public void saveInvoice(byte[] fileBytes, String id) {
		try {
//			String directoryPath = "C:\\Users\\17138\\Documents\\pic\\download";
			String directoryPath ="/home/ubuntu/CDC/Invoice";
			String fileName = "invoice_" + id + ".docx";
			Path filePath = Paths.get(directoryPath, fileName);
			Files.write(filePath, fileBytes);
			System.out.println("Invoice saved successfully: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to save invoice.");
		}
	}
	
	

	public void saveFile(byte[] fileBytes, String fileName) {
	    try {
//	    	String directoryPath = "C:\\Users\\17138\\Documents\\pic\\download";
			String directoryPath ="/home/ubuntu/CDC/Invoice";
	        Path filePath = Paths.get(directoryPath, fileName);
	        Files.write(filePath, fileBytes);
	        System.out.println("File saved successfully: " + filePath);
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to save file.");
	    }
	}


	@Autowired
	private JavaMailSender javaMailSender;

	public void sendInvoiceByEmail(byte[] fileBytes, String recipientEmail) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(recipientEmail);
		helper.setSubject("Invoice");
		helper.setText("Please find attached the invoice.");

		// Add the invoice attachment
		helper.addAttachment("invoice.docx", new ByteArrayDataSource(fileBytes,
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document"));

		javaMailSender.send(message);

		System.out.println("Invoice email sent successfully to: " + recipientEmail);
	}
	


	// ... Other imports ...
	
}
