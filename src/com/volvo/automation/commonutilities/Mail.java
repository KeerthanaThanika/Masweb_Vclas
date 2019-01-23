package com.volvo.automation.commonutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.volvo.reports.Reports;

import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;;

public class Mail extends Reports {
	protected static Properties prop;

	// public static void main(String[] args) {
	public static void sendmail() throws FileNotFoundException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Started");

		prop = new Properties();
		prop.load(new FileInputStream(new File("./mailUser.properties")));
		System.out.println(prop.getProperty("Mail.User.Name"));
		// String loc
		// =System.getProperty("user.dir")+"\reports\""+reportFileName+".html";
		File f = new File("reports");

		String location = f.getAbsolutePath() + "\\" + reportFileName + ".html";
		System.out.println(location);

		ExchangeClient client = new ExchangeClient.ExchangeClientBuilder().hostname("o365.mail.volvocars.com")
				.exchangeVersion(ExchangeVersion.Exchange2010_SP2).domain("vccnet")
				.username(prop.getProperty("Mail.User.Name")).password(prop.getProperty("Mail.User.Password"))
				.recipientTo(prop.getProperty("Mail.recipientTo.Name")).attachments(location)

				// .recipientCc("suganthi.mani@volvocars.com") // Ignore it in case you will not
				// use Cc recipients.
				// .recipientBcc("recipient@whatever.com") // Ignore it in case you will not use
				// Bcc recipients.

				.subject("Test message").message("Hi Please find the report attached.Thanks").build();

		client.sendExchange();

		System.out.println("Sent Successfully");
	}

	@Override
	public long takeSnap() {
		// TODO Auto-generated method stub
		return 0;
	}

}
