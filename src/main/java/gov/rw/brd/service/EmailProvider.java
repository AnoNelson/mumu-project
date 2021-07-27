package gov.rw.brd.service;


import gov.rw.brd.domain.Loanee;
import gov.rw.brd.domain.User;
import gov.rw.brd.exceptions.GlobalException;
import gov.rw.brd.repository.UserRepository;
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
    @Autowired
    private UserRepository repo;

    public String sendEmailAlert(String receiver, User user) {
        MimeMessage message = mailSender.createMimeMessage();
        if (receiver == null) {
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
                    user.getPassword() +
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
            helper.setSubject("FMOS");
            mailSender.send(message);
        } catch (Exception c) {
            System.out.println(c);
        }

        return "email sent";
    }

    public String sendApprovalEmaail(Loanee loanee,String action) {
        MimeMessage message = mailSender.createMimeMessage();
        if (loanee.getLoaneeEmail() == null) {
            throw new GlobalException("Email to send is null");
        }
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(repo.findById((long) loanee.getUser_id()).get().getEmail());
            helper.setText("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "  <title>testing pdf</title>\n" +
                    "  <style>\n" +
                    "    @import url(https://fonts.googleapis.com/css?family=Open+Sans:400,600);\n" +
                    "\n" +
                    "    *,\n" +
                    "    *:before,\n" +
                    "    *:after {\n" +
                    "      margin: 0;\n" +
                    "      padding: 0;\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    table {\n" +
                    "      width: 88%;\n" +
                    "      table-layout: fixed;\n" +
                    "      align-content: center;\n" +
                    "      text-align: center;\n" +
                    "      padding-left: 130px;\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    td {\n" +
                    "      padding: 9px;\n" +
                    "      text-align: left;\n" +
                    "      vertical-align: middle;\n" +
                    "      font-weight: 300;\n" +
                    "      font-size: 12px;\n" +
                    "      color: black;\n" +
                    "\n" +
                    "      border-bottom: solid 1px rgba(31, 22, 22, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "    /* demo styles */\n" +
                    "\n" +
                    "    @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    div {\n" +
                    "      padding-top: 30px;\n" +
                    "      padding-bottom: 70px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .buttom {\n" +
                    "      float: right;\n" +
                    "      padding-right: 150px;\n" +
                    "      padding-top: 40px;\n" +
                    "\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "\n" +
                    "  <div id=\":x4\" class=\"a3s aiL msg1149982382549468335\"><u></u>\n" +
                    "    <div style=\"margin:0;padding:0\" bgcolor=\"#FFFFFF\">\n" +
                    "      <table width=\"100%\" height=\"100%\" style=\"min-width:348px\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" lang=\"en\">\n" +
                    "        <tbody>\n" +
                    "          <tr height=\"32\" style=\"height:32px\">\n" +
                    "            <td></td>\n" +
                    "          </tr>\n" +
                    "          <tr align=\"center\">\n" +
                    "            <td>\n" +
                    "              <div>\n" +
                    "                <div></div>\n" +
                    "              </div>\n" +
                    "              <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                    "                style=\"padding-bottom:20px;max-width:516px;min-width:220px\">\n" +
                    "                <tbody>\n" +
                    "                  <tr>\n" +
                    "                    <td width=\"8\" style=\"width:8px\"></td>\n" +
                    "                    <td>\n" +
                    "                      <div\n" +
                    "                        style=\"border-style:solid;border-width:thin;border-color:#dadce0;border-radius:8px;padding:40px 20px\"\n" +
                    "                        align=\"center\" class=\"m_1149982382549468335mdv2rw\">\n" +
                    "                        <div\n" +
                    "                          style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;border-bottom:thin solid #dadce0;color:rgba(0,0,0,0.87);line-height:32px;padding-bottom:24px;text-align:center;word-break:break-word\">\n" +
                    "                          <div style=\"text-align:center;padding-bottom:16px;line-height:0\"><img height=\"33\"\n" +
                    "                              src=\"https://ci5.googleusercontent.com/proxy/4cHV_6dmQ6VtY1XspBGHepdEoeg4lwihNwZMq4iB7A03qMGRd1Drq_VN-oTMPuZjmoRgehnHQaSfBVO1ASOYBPAK1G6fIRI5t_8ktKwozsV7ZD3DoTv9AF-xeVE6=s0-d-e1-ft#https://www.gstatic.com/images/icons/material/system/2x/error_red_36dp.png\"\n" +
                    "                              class=\"CToWUd\"></div>\n" +
                    "                          <div style=\"font-size:24px\">Loan Request "+action+" </div>\n" +
                    "\n" +
                    "                        </div>\n" +
                    "                        <div>\n" +
                    "                        <a\n" +
                    "                              href=\"http://localhost:7878/view-requests\"\n" +
                    "                              style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;line-height:16px;color:#ffffff;font-weight:400;text-decoration:none;font-size:14px;display:inline-block;padding:10px 24px;background-color:#d94235;border-radius:5px;min-width:90px\"\n" +
                    "                              target=\"_blank\"\n" +
                    "                              data-saferedirecturl=\"https://www.google.com/url?q=https://accounts.google.com/AccountChooser?Email%3Dnelsishimwe@gmail.com%26continue%3Dhttps://myaccount.google.com/alert/nt/1624459830000?rfn%253D27%2526rfnc%253D1%2526eid%253D327810966241836694%2526et%253D0%2526anexp%253Dnret-fa&amp;source=gmail&amp;ust=1624825096483000&amp;usg=AFQjCNFYIv_56ZRN-EnW0LpCjTMRxEUL7Q\">Check\n" +
                    "                              activity</a></div>\n" +
                    "                        </div>\n" +
                    "                    </td>\n" +
                    "                  </tr>\n" +
                    "                </tbody>\n" +
                    "              </table>\n" +
                    "            </td>\n" +
                    "          </tr>\n" +
                    "        </tbody>\n" +
                    "      </table>\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>",true);
            helper.setSubject("FMOS");
            mailSender.send(message);
        } catch (Exception c) {
            System.out.println(c);
        }

        return "email sent";
    }

}
