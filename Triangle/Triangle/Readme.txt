Opgavebeskrivelse:
Programmet skal tage de tre sidelængder for en trekant som input, og angive om trekanten er ligesidet, ligebenet eller ”andet” som output.

Løsningsbeskrivelse:

Jeg har valgt at basere løsningen på 2 design patterns som er template method og strategy. Template method er valgt udfra tanken om at invariant behaviour og data implementeres en gang samt subklasser kun definere  det der variere i forhold til deres ansvarområde. Jeg mener man kan opbygge en fleksibelt vedligeholdelsesvenlig løsning ved dette design og opgaven givet. 
Strategy er implementeret såleds at f.eks triangle er konfigureret med en klasse der kan afgøre hvilken type triangle det er (EquilateralTriangle, IsoscelesTriangle, UnknownShape). Interfacet for denne afgørelse af type er fælles for alle typer. Det er template der styrer selve eksekveringen hviklet giver en mulighed for polymorphism f.eks igennem template.
Jeg har lagt vægt på en fornuftig code coverage ved unittests. Square er blot implementeret som en variant.

