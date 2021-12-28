import java.util.*;
import java.io.*;

public class Day16
{
    public static void main(String[] args) 
    {
        String input = "";
        String convertedInput = "";
        HashMap<Character, String> key = new HashMap<>();
        key.put('0', "0000");
        key.put('1', "0001");
        key.put('2', "0010");
        key.put('3', "0011");
        key.put('4', "0100");
        key.put('5', "0101");
        key.put('6', "0110");
        key.put('7', "0111");
        key.put('8', "1000");
        key.put('9', "1001");
        key.put('A', "1010");
        key.put('B', "1011");
        key.put('C', "1100");
        key.put('D', "1101");
        key.put('E', "1110");
        key.put('F', "1111");
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            input = br.readLine();
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }  
        for (int i = 0; i < input.length(); i++)
        {
            convertedInput += key.get(input.charAt(i));
        }
        Packet test = new Packet(convertedInput, 0);
        
        // Part B

        solve(test, test.getPacketType());
        System.out.println(test.getLiteralValue());

        // Part A

        // int versionSum = 0;
        // ArrayList<Packet> packetList = new ArrayList<>();
        // ArrayList<Packet> finalPacketList = new ArrayList<>();
        // ArrayList<Packet> toAdd = new ArrayList<>();
        // finalPacketList.add(test);
        // for (Packet p : test.getSubPackets())
        // {
        //     packetList.add(p);
        // }
        // int finalPacketListSize = -1;
        // while (finalPacketListSize != finalPacketList.size())
        // {
        //     finalPacketListSize = finalPacketList.size();
        //     for (Packet packet : packetList)
        //     {
        //         finalPacketList.add(packet);
        //         for (Packet p : packet.getSubPackets())
        //         {
        //             toAdd.add(p);
        //         }
        //     }
        //     packetList.clear();
        //     for (Packet p : toAdd)
        //     {
        //         packetList.add(p);
        //     }
        //     toAdd.clear();
        // }
        // for (Packet p : finalPacketList)
        // {
        //     versionSum += p.getPacketVersion();
        // }
        // System.out.println(versionSum);
    }

    private static void solve(Packet packet, int typeID)
    {
        if (packet.getPacketType() == 0)
        {
            for (Packet p : packet.getSubPackets())
            {
                if (p.getLiteralValue() == -1)
                {
                    solve(p, p.getPacketType());
                }
            }
            long value = 0;
            for (Packet p : packet.getSubPackets())
            {
                value += p.getLiteralValue();
            }
            packet.setLiteralValue(value);
        }
        else if (packet.getPacketType() == 1)
        {
            for (Packet p : packet.getSubPackets())
            {
                if (p.getLiteralValue() == -1)
                {
                    solve(p, p.getPacketType());
                }
            }
            long value = 1;
            for (Packet p : packet.getSubPackets())
            {
                value *= p.getLiteralValue();
            }
            packet.setLiteralValue(value);
        }
        else if (packet.getPacketType() == 2)
        {
            for (Packet p : packet.getSubPackets())
            {
                if (p.getLiteralValue() == -1)
                {
                    solve(p, p.getPacketType());
                }
            }
            long minValue = Long.MAX_VALUE;
            for (Packet p : packet.getSubPackets())
            {
                minValue = Math.min(p.getLiteralValue(), minValue);
            }
            packet.setLiteralValue(minValue);
        }
        else if (packet.getPacketType() == 3)
        {
            for (Packet p : packet.getSubPackets())
            {
                if (p.getLiteralValue() == -1)
                {
                    solve(p, p.getPacketType());
                }
            }
            long maxValue = Long.MIN_VALUE;
            for (Packet p : packet.getSubPackets())
            {
                maxValue = Math.max(p.getLiteralValue(), maxValue);
            }
            packet.setLiteralValue(maxValue);
        }
        else if (packet.getPacketType() == 5)
        {
            for (Packet p : packet.getSubPackets())
            {
                if (p.getLiteralValue() == -1)
                {
                    solve(p, p.getPacketType());
                }
            }
            if (packet.getSubPackets().get(0).getLiteralValue() > packet.getSubPackets().get(1).getLiteralValue())
            {
                packet.setLiteralValue(1);
            }
            else
            {
                packet.setLiteralValue(0);
            }
        }
        else if (packet.getPacketType() == 6)
        {
            for (Packet p : packet.getSubPackets())
            {
                if (p.getLiteralValue() == -1)
                {
                    solve(p, p.getPacketType());
                }
            }
            if (packet.getSubPackets().get(0).getLiteralValue() < packet.getSubPackets().get(1).getLiteralValue())
            {
                packet.setLiteralValue(1);
            }
            else
            {
                packet.setLiteralValue(0);
            }
        }
        else if (packet.getPacketType() == 7)
        {
            for (Packet p : packet.getSubPackets())
            {
                if (p.getLiteralValue() == -1)
                {
                    solve(p, p.getPacketType());
                }
            }
            if (packet.getSubPackets().get(0).getLiteralValue() == packet.getSubPackets().get(1).getLiteralValue())
            {
                packet.setLiteralValue(1);
            }
            else
            {
                packet.setLiteralValue(0);
            }
        }
    }
}