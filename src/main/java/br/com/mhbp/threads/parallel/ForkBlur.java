package br.com.mhbp.threads.parallel;

import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {

    private int[] source;
    private int start;
    private int length;
    private int[] destination;
    private int blurWidth = 15;

    public ForkBlur(int[] source, int start, int length, int[] destination) {
        this.source = source;
        this.start = start;
        this.length = length;
        this.destination = destination;
    }

    @Override
    protected void compute() {
        int threshold = 100;//?
        if (length < threshold) {
            computeDirectly();
            return;
        }

        int split = length / 2;

        invokeAll(new ForkBlur(source, start, split, destination),
                new ForkBlur(source, start + split, length - split,
                        destination));
    }

    protected void computeDirectly() {
        int sidePixels = (blurWidth - 1) / 2;
        for (int index = start; index < start + length; index++) {
            // Calculate average.
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0),
                        source.length - 1);
                int pixel = source[mindex];
                rt += (float)((pixel & 0x00ff0000) >> 16)
                        / blurWidth;
                gt += (float)((pixel & 0x0000ff00) >>  8)
                        / blurWidth;
                bt += (float)((pixel & 0x000000ff) >>  0)
                        / blurWidth;
            }

            // Reassemble destination pixel.
            int dpixel = (0xff000000     ) |
                    (((int)rt) << 16) |
                    (((int)gt) <<  8) |
                    (((int)bt) <<  0);
            destination[index] = dpixel;
        }
    }
}
