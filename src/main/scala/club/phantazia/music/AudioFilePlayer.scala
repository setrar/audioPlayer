package club.phantazia.music

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.{AudioFormat,AudioInputStream,AudioSystem}
import javax.sound.sampled.{SourceDataLine,DataLine}
 

class AudioFilePlayer {

  def play(fileName: String) {
     val in = AudioSystem.getAudioInputStream(new File(fileName));
     val ch = in.getFormat().getChannels();
     val rate = in.getFormat().getSampleRate();
     val outFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
     val line = AudioSystem.getLine(new DataLine.Info(classOf[SourceDataLine], outFormat)).asInstanceOf[SourceDataLine];
     val stream = AudioSystem.getAudioInputStream(outFormat, in);
 
     if (line != null) {
	     line.open(outFormat)
	     line.start()
	     dataStream(stream,line)
	     line.drain()
	     line.stop()
     }
  }
  
  // Ugly fix me
  def dataStream(stream: AudioInputStream,line: SourceDataLine) {
     var buffer:Array[Byte] = new Array[Byte](65536);
	 var n = 0
     while (n != -1 ) {
        n = stream.read(buffer, 0, buffer.length)
        line.write(buffer, 0, n)
     }
  }
}

object AudioFilePlayer {
  def main(args: Array[String]): Unit = {
        import java.io.File
        val sep = File.separator

        val player = new AudioFilePlayer ();
        player.play("src" + sep + "test" + sep + "resources" + sep + "WhatIsThisThingCalledLove.mp3")
        player.play("src" + sep + "test" + sep + "resources" + sep + "500MilesHigh.ogg")
  }
}
