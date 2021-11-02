package pe.edu.upc.SpringStartupInvest.util.startup;

import java.util.Comparator;

import pe.edu.upc.SpringStartupInvest.model.entity.Startup;

public class CompareAmounth implements Comparator<Startup> {

	@Override
	public int compare(Startup o1, Startup o2) {

		if (o1.getAmounthTotalInvest() > o2.getAmounthTotalInvest())
			return -1;
		else if (o1.getAmounthTotalInvest() > o2.getAmounthTotalInvest())
			return 0;
		else
			return 1;

	}

}
