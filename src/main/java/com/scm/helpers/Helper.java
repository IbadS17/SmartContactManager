package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // agar email is password se login kiya hai to : email kaise nikalenge
        if (authentication instanceof OAuth2AuthenticationToken) {

            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if (clientId.equalsIgnoreCase("google")) {

                // sign with google
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();

            } else if (clientId.equalsIgnoreCase("github")) {

                // sign with github
                System.out.println("Getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }

            // sign with facebook
            return username;

        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }

    }

    public static String getLinkForEmailVerification(String emailToken) {
        String link = "http://localhost:8080/auth/verify-email?token=" + emailToken;

        return link;
    }

    public static String getEmailVerificationTemplate(String verificationLink, String userName) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Email Verification</title>
            </head>
            <body style="margin: 0; padding: 0; font-family: 'Arial', sans-serif; background-color: #f4f4f4;">
                <table role="presentation" cellspacing="0" cellpadding="0" width="100%" style="background-color: #f4f4f4;">
                    <tr>
                        <td style="padding: 40px 20px;">
                            <table role="presentation" cellspacing="0" cellpadding="0" width="600" style="margin: 0 auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1);">
                                <!-- Header -->
                                <tr>
                                    <td style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); padding: 40px 30px; text-align: center; border-radius: 10px 10px 0 0;">
                                        <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: bold;">Smart Contact Manager</h1>
                                        <p style="margin: 10px 0 0 0; color: #f0f0f0; font-size: 14px;">Your Contacts, Smartly Managed</p>
                                    </td>
                                </tr>
                                
                                <!-- Main Content -->
                                <tr>
                                    <td style="padding: 40px 30px;">
                                        <h2 style="margin: 0 0 20px 0; color: #333333; font-size: 24px;">Welcome""" + (userName != null ? ", " + userName : "") + """
            !</h2>
                                        <p style="margin: 0 0 20px 0; color: #666666; font-size: 16px; line-height: 1.6;">
                                            Thank you for signing up with Smart Contact Manager! We're excited to have you on board.
                                        </p>
                                        <p style="margin: 0 0 30px 0; color: #666666; font-size: 16px; line-height: 1.6;">
                                            To get started and access all features, please verify your email address by clicking the button below:
                                        </p>
                                        
                                        <!-- CTA Button -->
                                        <table role="presentation" cellspacing="0" cellpadding="0" width="100%">
                                            <tr>
                                                <td style="text-align: center; padding: 20px 0;">
                                                    <a href=\"""" + verificationLink + """
            " style="display: inline-block; padding: 16px 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: #ffffff; text-decoration: none; border-radius: 50px; font-size: 16px; font-weight: bold; box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);">
                                                        Verify Email Address
                                                    </a>
                                                </td>
                                            </tr>
                                        </table>
                                        
                                        <!-- Alternative Link -->
                                        <p style="margin: 30px 0 0 0; color: #999999; font-size: 14px; line-height: 1.6;">
                                            If the button doesn't work, copy and paste this link into your browser:
                                        </p>
                                        <p style="margin: 10px 0 0 0; padding: 15px; background-color: #f8f9fa; border-left: 4px solid #667eea; word-break: break-all;">
                                            <a href=\"""" + verificationLink + """
            " style="color: #667eea; text-decoration: none; font-size: 14px;">""" + verificationLink + """
            </a>
                                        </p>
                                        
                                        <!-- Security Notice -->
                                        <div style="margin-top: 40px; padding: 20px; background-color: #fff3cd; border-left: 4px solid #ffc107; border-radius: 5px;">
                                            <p style="margin: 0; color: #856404; font-size: 14px; line-height: 1.6;">
                                                <strong>🔒 Security Note:</strong> This verification link will expire in 24 hours. If you didn't create an account with Smart Contact Manager, please ignore this email.
                                            </p>
                                        </div>
                                    </td>
                                </tr>
                                
                                <!-- Footer -->
                                <tr>
                                    <td style="background-color: #f8f9fa; padding: 30px; text-align: center; border-radius: 0 0 10px 10px;">
                                        <p style="margin: 0 0 10px 0; color: #666666; font-size: 14px;">
                                            Need help? Contact us at <a href="mailto:support@smartcontactmanager.com" style="color: #667eea; text-decoration: none;">support@smartcontactmanager.com</a>
                                        </p>
                                        <p style="margin: 0; color: #999999; font-size: 12px;">
                                            &copy; 2025 Smart Contact Manager. All rights reserved.
                                        </p>
                                        <p style="margin: 10px 0 0 0; color: #999999; font-size: 12px;">
                                            Made with ❤️ for better contact management
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """;
    }
}
