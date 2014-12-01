package club.phantazia.music

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.{AudioFormat,AudioInputStream,AudioSystem}
import javax.sound.sampled.{SourceDataLine,DataLine}
 

class AudioFilePlayer {

  def play(fileName: String) {
     val fileAudioStream = AudioSystem.getAudioInputStream(new File(fileName))
     val channels = fileAudioStream.getFormat().getChannels()
     val sampleRate = fileAudioStream.getFormat().getSampleRate()
     val sampleSizeInBits = 16
     val frameSize = channels * 2
     val frameRate = sampleRate
     val bigEndian = false
     val audioOutputFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, sampleSizeInBits, channels, frameSize, frameRate, bigEndian);
     val dataLineInfo = new DataLine.Info(classOf[SourceDataLine], audioOutputFormat)
     val audioStream = AudioSystem.getAudioInputStream(audioOutputFormat, fileAudioStream);
     val audioLine: Option[SourceDataLine] = Some(AudioSystem.getLine(dataLineInfo).asInstanceOf[SourceDataLine])
 
     audioLine match {
       case Some(line) => {
         line.open(audioOutputFormat)
	     line.start()
	     dataStream(audioStream,line)
	     line.drain()
	     line.stop()
       }
       case None => ???
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
//        player.play("src" + sep + "test" + sep + "resources" + sep + "WhatIsThisThingCalledLove.mp3")
        player.play("src" + sep + "test" + sep + "resources" + sep + "500MilesHigh.ogg")
  }
}
