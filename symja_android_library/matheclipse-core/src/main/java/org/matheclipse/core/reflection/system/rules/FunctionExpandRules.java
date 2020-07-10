package org.matheclipse.core.reflection.system.rules;

import static org.matheclipse.core.expression.F.*;
import org.matheclipse.core.interfaces.IAST;

/**
 * <p>Generated by <code>org.matheclipse.core.preprocessor.RulePreprocessor</code>.</p>
 * <p>See GIT repository at: <a href="https://github.com/axkr/symja_android_library">github.com/axkr/symja_android_library under the tools directory</a>.</p>
 */
public interface FunctionExpandRules {
  final public static IAST RULES = List(
    // ArcCot(Sqrt(x_^2)):=(Sqrt(x^2)*ArcCot(x))/x
    SetDelayed(ArcCot(Sqrt(Sqr(x_))),
      Times(Power(x,CN1),Sqrt(Sqr(x)),ArcCot(x))),
    // ArcSin(Sqrt(x_^2)):=(Sqrt(x^2)*ArcSin(x))/x
    SetDelayed(ArcSin(Sqrt(Sqr(x_))),
      Times(Power(x,CN1),Sqrt(Sqr(x)),ArcSin(x))),
    // ArcTan(Sqrt(x_^2)):=(Sqrt(x^2)*ArcTan(x))/x
    SetDelayed(ArcTan(Sqrt(Sqr(x_))),
      Times(Power(x,CN1),Sqrt(Sqr(x)),ArcTan(x))),
    // ChebyshevT(n_,x_):=Cos(n*ArcCos(x))
    SetDelayed(ChebyshevT(n_,x_),
      Cos(Times(n,ArcCos(x)))),
    // ChebyshevU(n_,x_):=Sin((1+n)*ArcCos(x))/(Sqrt(1-x)*Sqrt(1+x))
    SetDelayed(ChebyshevU(n_,x_),
      Times(Power(Times(Sqrt(Subtract(C1,x)),Sqrt(Plus(C1,x))),CN1),Sin(Times(Plus(C1,n),ArcCos(x))))),
    // Cos(n_Integer*ArcSin(z_)):=ChebyshevT(n,Sqrt(1-z^2))/;n>0
    SetDelayed(Cos(Times(ArcSin(z_),$p(n, Integer))),
      Condition(ChebyshevT(n,Sqrt(Subtract(C1,Sqr(z)))),Greater(n,C0))),
    // Fibonacci(m_Integer+n_):=1/2*Fibonacci(m)*LucasL(n)+1/2*Fibonacci(n)*LucasL(m)/;Element(n,Integers)
    SetDelayed(Fibonacci(Plus($p(m, Integer),n_)),
      Condition(Plus(Times(C1D2,Fibonacci(m),LucasL(n)),Times(C1D2,Fibonacci(n),LucasL(m))),Element(n,Integers))),
    // Fibonacci(n_+a_):=((2/(1+Sqrt(5)))^(-a-n)-Cos((a+n)*Pi)/(1/2*(1+Sqrt(5)))^(a+n))/Sqrt(5)/;Element(n,Integers)
    SetDelayed(Fibonacci(Plus(a_,n_)),
      Condition(Times(C1DSqrt5,Plus(Power(Times(C2,Power(Plus(C1,CSqrt5),CN1)),Subtract(Negate(a),n)),Times(CN1,Power(Times(C1D2,Plus(C1,CSqrt5)),Subtract(Negate(a),n)),Cos(Times(Plus(a,n),Pi))))),Element(n,Integers))),
    // Gamma(-1,z_):=1/(E^z*z)+ExpIntegralEi(-z)+1/2*(Log(-1/z)-Log(-z))+Log(z)
    SetDelayed(Gamma(CN1,z_),
      Plus(Power(Times(Exp(z),z),CN1),ExpIntegralEi(Negate(z)),Times(C1D2,Subtract(Log(Negate(Power(z,CN1))),Log(Negate(z)))),Log(z))),
    // Gamma(-1/2,z_):=2/(E^z*Sqrt(z))-2*Sqrt(Pi)*(1-Erf(Sqrt(z)))
    SetDelayed(Gamma(CN1D2,z_),
      Plus(Times(C2,Power(Times(Exp(z),Sqrt(z)),CN1)),Times(CN2,Sqrt(Pi),Subtract(C1,Erf(Sqrt(z)))))),
    // Gamma(0,z_):=-ExpIntegralEi(-z)+1/2*(-Log(-1/z)+Log(-z))-Log(z)
    SetDelayed(Gamma(C0,z_),
      Plus(Negate(ExpIntegralEi(Negate(z))),Times(C1D2,Plus(Negate(Log(Negate(Power(z,CN1)))),Log(Negate(z)))),Negate(Log(z)))),
    // Gamma(1/2,z_):=Sqrt(Pi)*(1-Erf(Sqrt(z)))
    SetDelayed(Gamma(C1D2,z_),
      Times(Sqrt(Pi),Subtract(C1,Erf(Sqrt(z))))),
    // GegenbauerC(n_,x_):=(2*Cos(n*ArcCos(x)))/n
    SetDelayed(GegenbauerC(n_,x_),
      Times(C2,Power(n,CN1),Cos(Times(n,ArcCos(x))))),
    // HarmonicNumber(n_):=EulerGamma+PolyGamma(0,1+n)
    SetDelayed(HarmonicNumber(n_),
      Plus(EulerGamma,PolyGamma(C0,Plus(C1,n)))),
    // HarmonicNumber(z_,n_):=-HurwitzZeta(n,1+z)+Zeta(n)
    SetDelayed(HarmonicNumber(z_,n_),
      Plus(Negate(HurwitzZeta(n,Plus(C1,z))),Zeta(n))),
    // HurwitzZeta(n_Integer,a_):=(-1)^n/(-1+n)!*PolyGamma(-1+n,a)/;n>1
    SetDelayed(HurwitzZeta($p(n, Integer),a_),
      Condition(Times(Power(CN1,n),Power(Factorial(Plus(CN1,n)),CN1),PolyGamma(Plus(CN1,n),a)),Greater(n,C1))),
    // Hypergeometric2F1(a_,b_,b_+n_Integer,z_):=Sum((z^k*Pochhammer(n,k)*Pochhammer(-a+b+n,k))/(Pochhammer(b+n,k)*k!),{k,0,-n})/(1-z)^(a-n)/;n<0
    SetDelayed(Hypergeometric2F1(a_,b_,Plus(b_,$p(n, Integer)),z_),
      Condition(Times(Power(Subtract(C1,z),Plus(Negate(a),n)),Sum(Times(Power(z,k),Power(Times(Pochhammer(Plus(b,n),k),Factorial(k)),CN1),Pochhammer(n,k),Pochhammer(Plus(Negate(a),b,n),k)),List(k,C0,Negate(n)))),Less(n,C0))),
    // LegendreQ(l_,m_,x_):=(-Pi*Csc(m*Pi)*Gamma(1+l+m)*LegendreP(l,-m,x))/(2*Gamma(1+l-m))+1/2*Pi*Cot(m*Pi)*LegendreP(l,m,x)
    SetDelayed(LegendreQ(l_,m_,x_),
      Plus(Times(CN1,Pi,Csc(Times(m,Pi)),Power(Times(C2,Gamma(Plus(C1,l,Negate(m)))),CN1),Gamma(Plus(C1,l,m)),LegendreP(l,Negate(m),x)),Times(C1D2,Pi,Cot(Times(m,Pi)),LegendreP(l,m,x)))),
    // LogisticSigmoid(x_):=1/(1+E^(-x))
    SetDelayed(LogisticSigmoid(x_),
      Power(Plus(C1,Exp(Negate(x))),CN1)),
    // PolyGamma(n_Integer,1/2):=(-1)^(n+1)*n!*(-1+2^(n+1))*Zeta(n+1)/;n>0
    SetDelayed(PolyGamma($p(n, Integer),C1D2),
      Condition(Times(Power(CN1,Plus(n,C1)),Factorial(n),Plus(CN1,Power(C2,Plus(n,C1))),Zeta(Plus(n,C1))),Greater(n,C0))),
    // ProductLog(x_*Log(x_)):=Log(x)/;x>1/E
    SetDelayed(ProductLog(Times(Log(x_),x_)),
      Condition(Log(x),Greater(x,Exp(CN1)))),
    // Sin(n_Integer*ArcSin(z_)):=z*ChebyshevU(-1+n,Sqrt(1-z^2))/;n>0
    SetDelayed(Sin(Times(ArcSin(z_),$p(n, Integer))),
      Condition(Times(z,ChebyshevU(Plus(CN1,n),Sqrt(Subtract(C1,Sqr(z))))),Greater(n,C0))),
    // Sin(n_Integer*ArcTan(z_)):=Sum((-1)^k*Binomial(n,2*k+1)*z^(2*k+1),{k,0,Floor(1/2*(-1+n))})/(1+z^2)^(n/2)/;n>0
    SetDelayed(Sin(Times(ArcTan(z_),$p(n, Integer))),
      Condition(Times(Power(Power(Plus(C1,Sqr(z)),Times(C1D2,n)),CN1),Sum(Times(Power(CN1,k),Binomial(n,Plus(Times(C2,k),C1)),Power(z,Plus(Times(C2,k),C1))),List(k,C0,Floor(Times(C1D2,Plus(CN1,n)))))),Greater(n,C0))),
    // Sinc(z_):=Sin(z)/z/;z!=0
    SetDelayed(Sinc(z_),
      Condition(Times(Power(z,CN1),Sin(z)),Unequal(z,C0))),
    // WhittakerM(k_,m_,z_):=(z^(1/2+m)*Hypergeometric1F1(1/2-k+m,1+2*m,z))/E^(z/2)
    SetDelayed(WhittakerM(k_,m_,z_),
      Times(Power(Exp(Times(C1D2,z)),CN1),Power(z,Plus(C1D2,m)),Hypergeometric1F1(Plus(C1D2,Negate(k),m),Plus(C1,Times(C2,m)),z))),
    // WhittakerW(k_,m_,z_):=(z^(1/2+m)*HypergeometricU(1/2-k+m,1+2*m,z))/E^(z/2)
    SetDelayed(WhittakerW(k_,m_,z_),
      Times(Power(Exp(Times(C1D2,z)),CN1),Power(z,Plus(C1D2,m)),HypergeometricU(Plus(C1D2,Negate(k),m),Plus(C1,Times(C2,m)),z)))
  );
}
