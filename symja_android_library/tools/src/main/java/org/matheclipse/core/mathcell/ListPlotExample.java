package org.matheclipse.core.mathcell;

import org.matheclipse.core.basic.Config;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.eval.exception.SymjaMathException;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.parser.client.SyntaxError;

public class ListPlotExample extends BasePlotExample {

	@Override
	public String exampleFunction() {
		return "ListPlot({{3.0,1.0},{67.0,1.0},{163.0,1.0}})";
		// return "ListPlot(Table({Sin(t*0.33), Cos(t*1.1)}, {t, 100}))";
		// return "ListPlot({3,Sin(1),Pi,3/4,42,1.2})";
		// return "ListPlot(Prime(Range(25)))";
		// return "ListPlot(Table({Sin(t), Cos(t)}, {t, 100}))";
		// return "Manipulate(ListPlot(Table({Sin(t*b), Cos(t*a)}, {t, 100})), {a,1,4,1}, {b,1,12})";
//		return "ListPlot(<|2 -> 1, 3 -> 2, 5 -> 3, 7 -> 4, 11 -> 5, 13 -> 6|>)";
	}

	public static void main(String[] args) {
		ListPlotExample p = new ListPlotExample();
		p.generateHTML();
	}

}
