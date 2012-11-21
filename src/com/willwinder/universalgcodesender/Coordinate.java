/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willwinder.universalgcodesender;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author wwinder
 */
public class Coordinate {
    private Double x,y,z;
    boolean s = false, s1 = false, s2 = false, s3 = false, s4 = false;
    String message, status1, mx, my, mz, wx, wy, wz;
    String status2;
    String status3, G5X, G5X_x,G5X_y,G5X_z;
    String settingNumber, settingValue, settingText;

    Coordinate(String msg) {
        message=msg;
        Pattern pattern1 = Pattern.compile("(([a-zA-Z]+)(,)(MPos:)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)(,)(WPos:)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*))");
        Matcher matcher1 = pattern1.matcher(msg);
        if (matcher1.find()){
            status1 = matcher1.group(2);
            mx = matcher1.group(5);
            my = matcher1.group(7);
            mz = matcher1.group(9);
            wx = matcher1.group(12);
            wy = matcher1.group(14);
            wz = matcher1.group(16);             
            this.x = Double.parseDouble(wx);
            this.y = Double.parseDouble(wy);
            this.z = Double.parseDouble(wz);
            s1 = true;
        }
        
        Pattern pattern2 = Pattern.compile("(([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*\\..\\d*))");
        Matcher matcher2 = pattern2.matcher(msg);
        if (matcher2.find()){
            status2 = matcher2.group(0);
            s2 = true;
        }
        
        Pattern pattern3 = Pattern.compile("([G]\\d*)(.?.?)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)");
        Matcher matcher3 = pattern3.matcher(msg);
        if (matcher3.find()){
            status3 = matcher3.group(0);
            G5X = matcher3.group(1);
            G5X_x = matcher3.group(3);
            G5X_y = matcher3.group(5);
            G5X_z = matcher3.group(7);
            status3 = G5X+": "+G5X_x+","+G5X_y+","+G5X_z;
            s3 = true;
        }
        
        Pattern pattern4 = Pattern.compile("((\\$\\d*)(=)(\\d.*\\d*)(.\\()(.*)(\\)))");
        Matcher matcher4 = pattern4.matcher(msg);
        if (matcher4.find()){
            settingNumber = matcher4.group(2);
            settingValue = matcher4.group(4);
            settingText = matcher4.group(6);
            s4 = true;
        }
        s = (s1||s2||s3||s4);
    }
    
    Double getX() {
        return this.x;
    }
    
    Double getY() {
        return this.y;
    }
    
    Double getZ() {
        return this.z;
    }
    
    @Override
    public String toString() {
        return "("+x+", "+y+", "+z+")";
    }
}
