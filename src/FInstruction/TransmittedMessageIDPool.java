/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FInstruction;

import java.util.ArrayList;
import java.util.Random;

/**
 * 信息ID池
 * @author sq
 */
public class TransmittedMessageIDPool {
    
    private String Owner;
    private ArrayList<String> IdPool;
    public static int MinNumber=100000;
    public static int MaxNumber=999999;
    public Random NumberCreater;

    public TransmittedMessageIDPool(String owner) {
        this.NumberCreater = new Random();
        this.Owner=owner;
        this.IdPool = new ArrayList<String>();
    }
    
    public String getOneRandomID(String Sender)
    {
        int randomnumber = NumberCreater.nextInt(MaxNumber)%(MaxNumber-MinNumber+1) + MinNumber;
        while(IdPool.contains(Integer.toString(randomnumber)) == true)
        {
            randomnumber = NumberCreater.nextInt(MaxNumber)%(MaxNumber-MinNumber+1) + MinNumber;
        }
        IdPool.add(Integer.toString(randomnumber));
        return Sender+"_"+Integer.toString(randomnumber);
    }
    
}
