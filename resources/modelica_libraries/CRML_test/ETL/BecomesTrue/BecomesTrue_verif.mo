within CRML_test.ETL.BecomesTrue;
model BecomesTrue_verif
  extends BecomesTrue;
  BecomesTrue_externals externals
    annotation (Placement(transformation(extent={{-200,0},{-140,60}})));
  CRML.Blocks.Events.ShowEvent show_c_b1_becomes_true
    annotation (Placement(transformation(extent={{52,-44},{60,-36}})));
equation
  // Bindings
  b1 = externals.b1;
  connect(show_c_b1_becomes_true.u, event4ToEvent.y) annotation (Line(points={{
          51.6,-40},{-20,-40},{-20,0},{-45.6,0}}, color={217,67,180}));
    annotation (Placement(transformation(extent={{0,-20},{60,40}})),
              Icon(coordinateSystem(preserveAspectRatio=false,
        extent={{-200,-100},{100,100}},
        initialScale=0.1),                                      graphics={
        Ellipse(lineColor = {75,138,73},
                fillColor={255,255,255},
                fillPattern = FillPattern.Solid,
                extent = {{-100,-100},{100,100}}),
        Polygon(lineColor = {0,0,255},
                fillColor = {75,138,73},
                pattern = LinePattern.None,
                fillPattern = FillPattern.Solid,
                points = {{-36,60},{64,0},{-36,-60},{-36,60}})}), Diagram(
        coordinateSystem(preserveAspectRatio=false,
        extent={{-200,-100},{100,100}},
        initialScale=0.1)),
    experiment(StopTime=14));
end BecomesTrue_verif;
