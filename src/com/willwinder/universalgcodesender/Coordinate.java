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
    boolean s, s1, s2;
    String message,status1,status2, mx, my, mz, wx, wy, wz;
    
    Coordinate(String msg) {
        message=msg;
        s1 = false;
        Pattern pattern1 = Pattern.compile("(([a-zA-Z]+)(,)(MPos:)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)(,)(WPos:)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)(,)(.\\d*\\..\\d*)())");
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
            s = (s1||s2);
        }
        s2 = false;
        Pattern pattern2 = Pattern.compile("(([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*) ([GMTF]\\d*\\..\\d*)())");
        Matcher matcher2 = pattern2.matcher(msg);
        if (matcher2.find()){
            status2 = matcher2.group(0);
            s2 = true;
        }
        
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
