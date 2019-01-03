package Controller.util;;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public SendMail(String message_from, String message_dest,String message_objet,String message_corps) {
        this.sendMail(message_from, message_dest, message_objet, message_corps);
    }


	public static void sendMail(String message_from, String message_dest,String message_objet,String message_corps){


		/** Objet session de JavaMail. */
		Session session;
		/** Objet message de JavaMail. */
		Message mesg;

		//		 Nous devons passer les informations au serveur de messagerie sous
		//		 forme de propri�t�s car JavaMail en comporte beaucoup...
		Properties props = new Properties();

		//		 Votre r�seau doit donner au serveur SMTP local le nom "nom_du_serveur_smtp"

		props.put("mail.smtp.host", "CasArray.radeema.local");

                props.put("mail.smtp.port", "25");

               props.setProperty("mail.smtp.auth", "false"); //Indicate that authentication is not required at smtp server
               
		session = Session.getDefaultInstance(props); // en cas de props.setProperty("mail.smtp.auth", "false")
		session.setDebug(true); //activer le mode verbeux !

		try {
			//		 Cr�er un message.
			mesg = new MimeMessage(session);

			//		 Adresse From - Indiquer la provenance du message
			mesg.setFrom(new InternetAddress(message_from));

			//		 Adresse TO.
			InternetAddress toAddress = new InternetAddress(message_dest);
			mesg.addRecipient(Message.RecipientType.TO, toAddress);

			//		 Objet.
			mesg.setSubject(message_objet);

			//		 Corps du message.
//			mesg.setText(message_corps);
                        mesg.setContent(message_corps, "text/html");

			//		 Enfin, envoyer le message !
			Transport.send(mesg);

		} catch (MessagingException ex) {
			while ((ex = (MessagingException)ex.getNextException()) != null) {
				ex.printStackTrace();
			}
                        System.out.println("echec");
		}
	}

	
}