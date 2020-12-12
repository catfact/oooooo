Engine_SimpleDelay : CroneEngine {
  var synth;

  *new { arg context, doneCallback;
    ^super.new(context, doneCallback);
  }

  alloc { 
    synth = { arg delay=0.001, volume=0.0;
      var input = SoundIn.ar([0, 1]);
      DelayN.ar(input, maxDelayTime:0.002, delayTime:delay, mul:volume)
    }.play(context.server);

    this.addCommand("delay", "f", { arg msg; synth.set(\delay, msg[1]); });
    this.addCommand("volume", "f", { arg msg; synth.set(\volume, msg[1]); });
  }
}