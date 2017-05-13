{
  D(ArcCos(x_),x_):=D(x,x)*(-1)*(1-x^2)^(-1/2),
  D(ArcCosh(x_),x_):=D(x,x)*(x^2-1)^(-1/2),
  D(ArcCot(x_),x_):=D(x,x)*(-1)*(1+x^2)^(-1),
  D(ArcCoth(x_),x_):=D(x,x)*(1-x^2)^(-1),
  D(ArcCsc(x_),x_):=-D(x,x)*1*x^(-2)*(1-x^(-2))^(-1/2),
  D(ArcCsch(x_),x_):=D(x,x)*(-1)*Abs(x)^(-1)*(1+x^2)^(-1/2),
  D(ArcSin(x_),x_):=D(x,x)*(1-x^2)^(-1/2),
  D(ArcSinh(x_),x_):=D(x,x)*(1+x^2)^(-1/2),
  D(ArcTan(x_),x_):=D(x,x)*(1+x^2)^(-1),
  D(ArcTanh(x_),x_):=D(x,x)*(1-x^2)^(-1),
  D(ArcSec(x_),x_):=D(x,x)*x^(-2)*(1-x^(-2))^(-1/2),
  D(ArcSech(x_),x_):=D(x,x)*(-1)*x^(-1)*(1-x^2)^(-1/2),
  D(Ceiling(x_),x_):=0,
  D(Erf(x_),x_):=D(x,x)*(2*E^(-x^(2))/Sqrt(Pi)),
  D(Erfc(x_),x_):=D(x,x)*(-2*E^(-x^(2))/Sqrt(Pi)),
  D(Erfi(x_),x_):=D(x,x)*(2*E^(x^(2))/Sqrt(Pi)),
  D(Floor(x_),x_):=0,
  D(FractionalPart(x_),x_):=D(x,x)*1,
  D(FresnelC(x_),x_):=D(x,x)*Cos((Pi*x^2)/2),
  D(FresnelS(x_),x_):=D(x,x)*Sin((Pi*x^2)/2),
  D(Gamma(x_),x_):=D(x,x)*Gamma(x)*PolyGamma(x),
  D(IntegerPart(x_),x_):=0,
  D(InverseErf(x_),x_):=D(x,x)*(1/2*Sqrt(Pi)*E^(InverseErf(x)^2)),
  D(Log(x_),x_):=D(x,x)*x^(-1),
  D(PolyGamma(x_),x_):=D(x,x)*PolyGamma(1,x),
  D(Cot(x_),x_):=D(x,x)*(-1)*Csc(x)^2,
  D(Coth(x_),x_):=D(x,x)*(-1)*Sinh(x)^(-2),
  D(Cos(x_),x_):=D(x,x)*(-1)*Sin(x),
  D(Cosh(x_),x_):=D(x,x)*Sinh(x),
  D(Csc(x_),x_):=D(x,x)*(-1)*Cot(x)*Csc(x),
  D(Csch(x_),x_):=D(x,x)*(-1)*Coth(x)*Csch(x),
  D(Round(x_),x_):=0,
  D(Sin(x_),x_):=D(x,x)*Cos(x),
  D(Sinh(x_),x_):=D(x,x)*Cosh(x),
  D(Tan(x_),x_):=D(x,x)*Sec(x)^2,
  D(Tanh(x_),x_):=D(x,x)*Sech(x)^(2),
  D(Sec(x_),x_):=D(x,x)*Sec(x)*Tan(x),
  D(Sech(x_),x_):=D(x,x)*(-1)*Tanh(x)*Sech(x)
}