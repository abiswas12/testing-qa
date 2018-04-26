package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Cookies {
    private WebDriver browser;
    private String url;
    public Cookies(WebDriver browser, String url) {
        this.browser = browser;
        this.url=url;
    }

    public void createCookie() {
        if(url.equals(null)) { System.err.println("You need to instantiate with a variable");}

        File f;
        BufferedWriter buf = null;

        try {
            f = new File("browser.data");
            f.delete();
            f.createNewFile();
            buf = new BufferedWriter(new FileWriter(f));

            for (Cookie ck : browser.manage().getCookies()) {
                buf.write(ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
                        + ck.getExpiry() + ";" + ck.isSecure());
                buf.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buf != null) {
                    buf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void loadCookie() {

        try {
            File f = new File("browser.data");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String dt;

                    if (!(dt = str.nextToken()).equals("null")) {
                        expiry = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(dt);
                    }
                    boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    browser.manage().addCookie(ck);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        browser.get(url);
    }
}
