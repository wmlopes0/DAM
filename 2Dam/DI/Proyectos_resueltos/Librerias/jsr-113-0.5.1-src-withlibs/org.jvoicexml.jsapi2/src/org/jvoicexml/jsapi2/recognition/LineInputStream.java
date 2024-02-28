/**
 * 
 */
package org.jvoicexml.jsapi2.recognition;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * An {@link InputStream} that reads the data from the microphone.
 * @author Dirk Schnelle-Walka
 *
 */
public final class LineInputStream extends InputStream {
    /** Logger for this class. */
    private static final Logger LOGGER =
            Logger.getLogger(LineInputStream.class.getName());

    /** The line to read the audio from. */
    private TargetDataLine line;

    /** The audio format. */
    private AudioFormat format;

    /**
     * Constructs a new object.
     * @param audioFormat the audio format to read from the microphone.
     */
    public LineInputStream(final AudioFormat audioFormat) {
        format = audioFormat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read() throws IOException {
        final byte[] buffer = new byte[1];
        return read(buffer, 0, buffer.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(final byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(final byte[] buffer, final int off, final int len)
        throws IOException {
        if (buffer == null) {
            throw new NullPointerException("buffer must not be null");
        } else if (off < 0 || len < 0 || len > buffer.length - off) {
            throw new IndexOutOfBoundsException(
                    "offset and length do not match buffer size");
        } else if (len == 0) {
            return 0;
        }
        if (line == null) {
            getLine();
        }
        return line.read(buffer, off, len);
    }


    /**
     * Retrieves a line to read the audio data.
     * @throws IOException
     *         error opening the line.
     */
    private void getLine() throws IOException {
        try {
            final DataLine.Info info =
                new DataLine.Info(TargetDataLine.class, format);

            line = (TargetDataLine) AudioSystem.getLine(info);
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("opened line " + line + " with format " + format);
            }
            line.open();
            line.start();
        } catch (LineUnavailableException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        if (line != null) {
            line.stop();
            line.close();
            line = null;
        }
        super.close();
    }
}
