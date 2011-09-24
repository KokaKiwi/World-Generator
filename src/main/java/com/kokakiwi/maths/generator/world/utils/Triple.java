package com.kokakiwi.maths.generator.world.utils;

public class Triple<V, T, U>
{
    private final V v;
    private final T t;
    private final U u;
    
    public Triple(V v, T t, U u)
    {
        this.v = v;
        this.t = t;
        this.u = u;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((t == null) ? 0 : t.hashCode());
        result = prime * result + ((u == null) ? 0 : u.hashCode());
        result = prime * result + ((v == null) ? 0 : v.hashCode());
        return result;
    }
}
