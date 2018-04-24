package utils;

import java.io.File;

public enum Constants {
    URLTODEMO ("http://thedemosite.co.uk/index.php"),
    URLTOQA   ("http://www.qa.com"),
    URLTOMOUSEDEMO  ("http://demoqa.com/"),
    PATHTO   (System.getProperty("user.dir") + File.separatorChar),
    REPORT   ("Report"+".html"),
    EXCEL   ("Book1"+".xlsx");

    private final String stuff;   // in String
    Constants( String stuff) {
        this.stuff = stuff;
    }

    public String getStuff() {
        return stuff;
    }
}
