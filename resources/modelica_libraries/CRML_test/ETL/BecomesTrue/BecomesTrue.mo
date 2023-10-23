within CRML_test.ETL.BecomesTrue;
partial model BecomesTrue
  Utilities.Boolean4Connector b1
    annotation (Placement(transformation(extent={{-120,10},{-100,-10}})));
  Utilities.ClockConnector c_b1_becomes_true
    annotation (Placement(transformation(extent={{100,10},{120,-10}})));
  CRML.Blocks.Events.ClockEvent clockEvent
    annotation (Placement(transformation(extent={{-6,-6},{6,6}})));
  CRML.Blocks.Events.Event4ToEvent event4ToEvent
    annotation (Placement(transformation(extent={{-54,-4},{-46,4}})));
equation
  connect(clockEvent.y, c_b1_becomes_true) annotation (Line(
      points={{6.6,0},{110,0}},
      color={175,175,175},
      pattern=LinePattern.Dot,
      thickness=0.5));
  connect(b1, event4ToEvent.u)
    annotation (Line(points={{-110,0},{-54.4,0}}, color={0,0,0}));
  connect(clockEvent.u, event4ToEvent.y)
    annotation (Line(points={{-6.6,0},{-45.6,0}}, color={217,67,180}));
  annotation (Icon(coordinateSystem(preserveAspectRatio=false)), Diagram(
        coordinateSystem(preserveAspectRatio=false)));
end BecomesTrue;
