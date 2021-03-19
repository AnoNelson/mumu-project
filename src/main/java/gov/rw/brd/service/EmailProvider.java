package gov.rw.brd.service;


import gov.rw.brd.domain.User;
import gov.rw.brd.exceptions.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
/**
 * Created by Nick on 4/2/2020.
 */
@Service
public class EmailProvider {
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmailAlert(String receiver,User user) {
        MimeMessage message = mailSender.createMimeMessage();
         if(receiver ==null){
             throw new GlobalException("Email to send is null");
         }
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(receiver);
//            helper.setFrom("icrw@gtbank.com");
            helper.setText("\n" +
                    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                    "<html style=\"-moz-osx-font-smoothing: grayscale; -webkit-font-smoothing: antialiased; background-color: #464646; margin: 0; padding: 0;\">\n" +
                    "    <head>\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "        <meta name=\"format-detection\" content=\"telephone=no\">\n" +
                    "        \n" +
                    "    </head>\n" +
                    "    <body bgcolor=\"#d7d7d7\" class=\"generic-template\" style=\"-moz-osx-font-smoothing: grayscale; -webkit-font-smoothing: antialiased; background-color: #d7d7d7; margin: 0; padding: 0;\">\n" +
                    "        <!-- Header Start -->\n" +
                    "        <!-- Header End -->\n" +
                    "\n" +
                    "        <!-- Content Start -->\n" +
                    "        <table cellpadding=\"0\" cellspacing=\"0\" cols=\"1\" bgcolor=\"#d7d7d7\" align=\"center\" style=\"max-width: 600px;\">\n" +
                    "            <tr bgcolor=\"#d7d7d7\">\n" +
                    "                <td height=\"50\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "            </tr>\n" +
                    "\n" +
                    "            <!-- This encapsulation is required to ensure correct rendering on Windows 10 Mail app. -->\n" +
                    "            <tr bgcolor=\"#d7d7d7\">\n" +
                    "                <td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                    <!-- Seperator Start -->\n" +
                    "                    <table cellpadding=\"0\" cellspacing=\"0\" cols=\"1\" bgcolor=\"#d7d7d7\" align=\"center\" style=\"max-width: 600px; width: 100%;\">\n" +
                    "                        <tr bgcolor=\"#d7d7d7\">\n" +
                    "                            <td height=\"30\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                        </tr>\n" +
                    "                    </table>\n" +
                    "                    <!-- Seperator End -->\n" +
                    "\n" +
                    "                    <!-- Generic Pod Left Aligned Start -->\n" +
                    "                    <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" cols=\"3\" bgcolor=\"white\" class=\"bordered-left-right\" style=\"border-left: 10px solid #d7d7d7; border-right: 10px solid #d7d7d7; max-width: 600px; width: 100%;\">\n" +
                    "                        <tr height=\"50\"><td colspan=\"3\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "                        <tr align=\"center\">\n" +
                    "                            <td width=\"36\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                            <td class=\"text-primary\" style=\"color: #F16522; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                                <h1 style=\"color: #F16522; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 30px; font-weight: 700; line-height: 34px; margin-bottom: 0; margin-top: 0;\">Application Credentail</h1>\n" +
                    "                            </td>\n" +
                    "                            <td width=\"36\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr height=\"30\"><td colspan=\"3\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "                        <tr align=\"left\">\n" +
                    "                            <td width=\"36\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                            <td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                                <p style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">Hi,  \n" +
                    "</p>\n" +
                    "                                <br>\n" +
                    "                                <p style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">\n" +
                    "                                  click the link below to activate your acount\n" +
                    "</p>\n" +
                    "<br/>\n" +
                    "                         <div align=\"center\" style=\"padding:14px 20px 14px 20px;background-color:#F16522;border-radius:4px\">\n" +
                    "<a href=\"#\" style=\"font-family:helvetica neue,helvetica,arial,sans-serif;font-weight:bold;font-size:18px;line-height:1.5;color:#ffffff;text-decoration:none;display:block;text-align:center;max-width:400px;overflow:hidden;text-overflow:ellipsis\">\n" +
                    user.getPassword()+
                    "</a>\n" +
                    "</div>\n" +
                    "<br/><br/>\n" +
                    "          \n" +
                    "                                                              <p style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">\n" +
                    "\n" +
                    "Best regards\n" +

                    "                            </td>\n" +
                    "  \n" +
                    "                        </tr>\n" +
                    "\n" +
                    "                        \n" +
                    "       \n" +
                    "                        <tr height=\"50\"><td colspan=\"3\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "                    </table>\n" +
                    "                    <!-- Generic Pod Left Aligned End -->\n" +
                    "\n" +
                    "                    <!-- Seperator Start -->\n" +
                    "        <!-- Content End -->\n" +
                    "\n" +
                    "    </body>\n" +
                    "</html>", true);
            helper.setSubject("INSURANCE ALERT");
            mailSender.send(message);
        } catch (Exception c) {
            System.out.println(c);
        }

        return "email sent";
    }

}
