/**
 * 
 */
package hsl.p2pipcam.manager.listener;

/**
 * @author Administrator
 *
 */
public interface RecorderListener
{
	void callBack_RecordPlayPos(long userid, int pos);
	void callBackAudioData(long userID, byte[] pcm, int size);
}
