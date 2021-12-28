import java.util.*;

public class SnailNumber
{
    private SnailNumber left, right, parent;
    private Integer value;

    public SnailNumber(SnailNumber l, SnailNumber r)
    {
        left = l;
        right = r;
        value = null;
        l.setParent(this);
        r.setParent(this);
        reduce();
    }

    public SnailNumber(int v)
    {
        value = v;
        parent = null;
    }

    public SnailNumber getParent()
    {
        return parent;
    }

    public SnailNumber getLeft()
    {
        return left;
    }

    public SnailNumber getRight()
    {
        return right;
    }

    public void setRight(SnailNumber r)
    {
        right = r;
    }

    public void setLeft(SnailNumber l)
    {
        left = l;
    }

    public void setParent(SnailNumber p)
    {
        parent = p;
    }

    public int getLevel()
    {
        if (parent == null)
        {
            return 0;
        }
        return parent.getLevel() + 1;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int v)
    {
        value = v;
    }

    public int getMagnitude()
    {
        if (isSingle())
        {
            return value;
        }
        return 3 * left.getMagnitude() + 2 * right.getMagnitude();
    }

    public SnailNumber getOrigin()
    {
        SnailNumber curr = this;
        while (curr.getParent() != null)
        {
            curr = curr.getParent();
        }
        return curr;
    }

    public List<SnailNumber> getAllNonPairs()
    {
        if (isSingle())
        {
            return List.of(this);
        }
        List<SnailNumber> allNonPairs = new ArrayList<>();
        allNonPairs.addAll(left.getAllNonPairs());
        allNonPairs.addAll(right.getAllNonPairs());
        return allNonPairs;
    }

    private SnailNumber numberInRange(List<SnailNumber> list, int i)
    {
        return (i < 0 || i >= list.size()) ? null : list.get(i);
    }

    public SnailNumber nearestNonPair(SnailNumber num, boolean travelLeft)
    {
        List<SnailNumber> nonPairs = getOrigin().getAllNonPairs();
        return numberInRange(nonPairs, nonPairs.indexOf(num.getLeft()) + (travelLeft ? -1 : 2));
    }

    public SnailNumber add(SnailNumber toAdd)
    {
        SnailNumber sn = new SnailNumber(this, toAdd);
        sn.reduce();
        return sn;
    }

    public void reduce()
    {
        boolean canContinue = true;
        while (canContinue)
        {
            canContinue = false;
            while (explode())
            {
                canContinue = true;
            }
            canContinue = canContinue || split();
        }
    }

    public boolean explode()
    {
        if (!isSingle())
        {
            if (getLevel() >= 4)
            {
                SnailNumber nearestLeft = nearestNonPair(this, true);
                SnailNumber nearestRight = nearestNonPair(this, false);
                if (nearestLeft != null)
                {
                    nearestLeft.setValue(nearestLeft.getValue() + left.getValue());
                }
                if (nearestRight != null)
                {
                    nearestRight.setValue(nearestRight.getValue() + right.getValue());
                }
                left = null;
                right = null;
                value = 0;
                return true;
            }
            else
            {
                if (left.explode())
                {
                    return true;
                }
                if (right.explode())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean split()
    {
        if (isSingle())
        {
            if (value >= 10)
            {
                left = new SnailNumber(value / 2);
                right = new SnailNumber(value / 2 + value % 2);
                left.setParent(this);
                right.setParent(this);
                value = null;
                return true;
            }
        }
        else 
        {
            if (left.split())
            {
                return true;
            }
            if (right.split())
            {
                return true;
            }
        }
        return false;
    }

    public boolean isSingle()
    {
        return value != null;
    }

    public SnailNumber clone()
    {
        if (isSingle())
        {
            return new SnailNumber(value);
        }
        return new SnailNumber(left.clone(), right.clone());
    }

    public String toString()
    {
        if (isSingle())
        {
            return value.toString();
        }
        return "[" + left + "," + right + "]";
    }
}