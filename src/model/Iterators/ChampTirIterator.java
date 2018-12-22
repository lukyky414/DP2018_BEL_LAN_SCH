package model.Iterators;

import model.ChampTir;

import java.awt.*;
import java.util.Iterator;

public class ChampTirIterator implements Iterator<Boolean> {
	private ChampTir champTir;
	private int x, y;

	public ChampTirIterator(ChampTir champTir){
		this.champTir = champTir;
		x = y = 0;
	}


	@Override
	public boolean hasNext() {
		return y < 10;
	}

	@Override
	public Boolean next() {
		Point pos = new Point(x,y);

		if(++x == 10){
			++y;
			x = 0;
		}

		return champTir.estTouche(pos);
	}
}
