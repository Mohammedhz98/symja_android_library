package org.matheclipse.core.reflection.system;

import org.matheclipse.core.eval.interfaces.AbstractFunctionEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.generic.ITernaryComparator.COMPARE_RESULT;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.list.algorithms.EvaluationSupport;

public class Max extends AbstractFunctionEvaluator {
	public Max() {
	}

	public IExpr evaluate(final IAST ast) {
		IAST list = ast;
		if (list.size() > 1) {
			IAST resultList = list.copyHead();
			if (EvaluationSupport.flatten(F.List, list, resultList)) {
				list = resultList;
			}
			// IExpr max = F.Times(F.CN1, ExprFactory.Infinity);
			IExpr max1;
			IExpr max2;
			max1 = list.get(1);
			IAST f = list.copyHead();
			COMPARE_RESULT comp;
			for (int i = 2; i < list.size(); i++) {
				max2 = list.get(i);
				comp = Less.CONST.prepareCompare(max1, max2);

				if (comp == COMPARE_RESULT.TRUE) {
					max1 = max2;
				} else {
					if (comp == COMPARE_RESULT.UNDEFINED) {
						// undetermined
						if (max1.isNumber()) {
							f.add(max2);
						} else {
							f.add(max1);
							max1 = max2;
						}
					}
				}
			}
			if (f.size() > 1) {
				f.add(1, max1);
				if (f.equals(list)) {
					return null;
				}
				return f;
			} else {
				return max1;
			}
		}
		return null;
	}

}
