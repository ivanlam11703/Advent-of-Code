public class Octopus
{
    private int energy;
    private boolean hasFlashed;

    public Octopus(int e)
    {
        hasFlashed = false;
        energy = e;
    }

    public void increaseEnergy()
    {
        if (!hasFlashed)
        {
            energy = energy + 1;
        }
    }

    public int getEnergy()
    {
        return energy;
    }

    public boolean hasFlashed()
    {
        return hasFlashed;
    }

    public void resetFlash()
    {
        hasFlashed = false;
    }

    public void flash()
    {
        energy = 0;
        hasFlashed = true;
    }
}