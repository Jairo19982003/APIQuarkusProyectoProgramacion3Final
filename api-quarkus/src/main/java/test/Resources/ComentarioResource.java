package test.Resources;

import io.quarkus.mailer.reactive.ReactiveMailer;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Comentario;
import test.Services.Implementacion.ComentarioService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

@Path("/Comentario")
public class ComentarioResource {

    @Inject
    ComentarioService comentario;

    @Inject
    ReactiveMailer reactiveMailer;

    // Ruta del archivo CSV
    File csvFile = new File("/ruta/al/archivo.csv");

    @GET
    @Path("/comentarioId")
    //se utiliza para obtener un comentario
    public Comentario getComentario() {
        System.out.println("Comentario: " + comentario.getComentario());
        return comentario.getComentario();
    }


    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    //se utiliza para guardar un comentario
    public Response createComentario(Comentario Rcomentario) {
        try{
            System.out.printf("Comentario: %s\n", Rcomentario);
                comentario.save(Rcomentario);
                sendEmail(Rcomentario);
                comentario.saveFile(Rcomentario);
        return Response.ok("Comentario guardado"+ Rcomentario).build();
        }catch (PersistenceException e){
            throw e;
//            return Response.status(Response.Status.BAD_REQUEST).entity("Error al guardar el comentario").build();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    //http://localhost:8080/Comentario/paginated?page=2
    public PaginatedReponse<Comentario> list(@QueryParam("page")@DefaultValue("1") int page) throws MessagingException {
        return comentario.paginated(page);
    }

    public static void sendEmail(Comentario comentario) throws MessagingException {
        final String username = "castillo.jairo99930@gmail.com"; // Tu dirección de correo
        final String password = "gsop eknz bhos rgpf"; // Tu contraseña

        // Propiedades de conexión SMTP
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        // Autenticación
        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("castillo.jairo99930@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(comentario.getEmailUser())
            );
            message.setSubject("Agradecimiento");
            message.setText("Hola, Gracias por realizar el comentario\n\n" + "comentario realizado \n\n" + comentario.getComentario());

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Mensaje enviado exitosamente");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailAdmin (File csvFile) throws MessagingException {
        final String username = "castillo.jairo99930@gmail.com"; // Tu dirección de correo
        final String password = "gsop eknz bhos rgpf"; // Tu contraseña

        // Propiedades de conexión SMTP
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        // Autenticación
        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("castillo.jairo99930@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("castillo.jairo99930@gmail.com")
            );
            message.setSubject("Informe");


            // Cuerpo del mensaje
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Este correo es informa cuales son los comentarios realizando en el dia de hoy");

            // Parte del archivo adjunto
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(csvFile);

            // Combinar partes en un multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Configurar el contenido del mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Mensaje enviado exitosamente");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0 0 22 * * ?")
    public void executeDailyTask() throws MessagingException {
        String fileName = "C:\\Users\\admin\\Desktop\\Project_quarkus\\api-quarkus\\" + LocalDate.now() + "_comentarios.csv";
        File csvFile = new File(fileName);
        sendEmailAdmin(csvFile);
    }

}
