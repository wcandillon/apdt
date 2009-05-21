package org.phpaspect.core.weaver;

public class Pair<T1, T2> {

	private T1 index;
	private T2 value;
	
	public Pair(){}
	
	public Pair(T1 index, T2 value)
	{
		setIndex(index).setValue(value);
	}
	
	public Pair<T1, T2> setIndex(T1 index)
	{
		this.index = index;
		return this;
	}
	
	public Pair<T1, T2> setValue(T2 value)
	{
		this.value = value;
		return this;
	}
	
	public T1 getIndex()
	{
		return index;
	}
	
	public T2 getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return index+":"+value;
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Pair<?, ?>)
		{
			Pair<?, ?> pair = (Pair<?, ?>)o;
			return pair.getIndex().equals(index) &&
				pair.getValue().equals(value);
		}
		return false;
	}
}
