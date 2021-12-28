import java.util.ArrayList;

public class Packet
{
    private int numSubPackets, lenSubPackets, packetVersion, lengthTypeID, packetType, totalLength;
    private long literalValue;
    private ArrayList<Packet> subPackets;

    public Packet(String input, int index)
    {
        subPackets = new ArrayList<>();
        int initialLength = index;
        packetVersion = Integer.parseInt(input.substring(index,index += 3), 2);
        packetType = Integer.parseInt(input.substring(index,index += 3), 2);
        if (packetType == 4)
        {
            String literalString = "";
            String bitString = input.substring(index, index += 5);
            while (bitString.charAt(0) != '0')
            {
                literalString += bitString.substring(1);
                bitString = input.substring(index, index += 5);
            }
            literalString += bitString.substring(1);
            literalValue = Long.parseLong(literalString, 2);
            numSubPackets = 0;
            lengthTypeID = -1;
            lenSubPackets = -1;
            totalLength = index - initialLength;
            return;
        }
        lengthTypeID = Integer.parseInt(input.substring(index, index += 1));
        if (lengthTypeID == 1)
        {
            numSubPackets = Integer.parseInt(input.substring(index, index += 11), 2);
            lenSubPackets = -1;
            for (int i = 0; i < numSubPackets; i++)
            {
                Packet toAdd = new Packet(input, index);
                index += toAdd.getTotalLength();
                subPackets.add(toAdd);
            }
        }
        else
        {
            lenSubPackets = Integer.parseInt(input.substring(index, index += 15), 2);
            numSubPackets = -1;
            int currentIndex = index;
            while (index < lenSubPackets + currentIndex)
            {
                Packet toAdd = new Packet(input, index);
                index += toAdd.getTotalLength();
                subPackets.add(toAdd);
            }
        }
        literalValue = -1;
        totalLength = index - initialLength;
    }
    
    public long getLiteralValue()
    {
        return literalValue;
    }

    public void setLiteralValue(long lv)
    {
        literalValue = lv;
    }

    public int getPacketType()
    {
        return packetType;
    }

    public int getNumSubPackets()
    {
        return numSubPackets;
    }

    public int getLenSubPackets()
    {
        return lenSubPackets;
    }

    public int getLengthTypeID()
    {
        return lengthTypeID;
    }

    public int getPacketVersion()
    {
        return packetVersion;
    }

    public ArrayList<Packet> getSubPackets()
    {
        return subPackets;
    }

    public int getTotalLength()
    {
        return totalLength;
    }

    public String toString()
    {
        String toReturn = "Packet version: " + packetVersion + "\n";
        toReturn += "Packet type: " + packetType + "\n";
        toReturn += "Length type ID: " + lengthTypeID + "\n";
        toReturn += "Number of subpackets: " + numSubPackets + "\n";
        toReturn += "Length of subpackets: " + lenSubPackets + "\n";
        toReturn += "Literal value: " + literalValue + "\n";
        toReturn += "Total length: " + totalLength;
        return toReturn;
    }
}