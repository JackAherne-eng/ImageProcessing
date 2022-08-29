package com.example.pcbanalyserrepeat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PCBControllerTest {

    ArrayList<Integer> roots = new ArrayList<>();

    @Test
    public void testImage() {

        // Loads the image

        int width = 4;
        int height = 4;
        int[] image = {0, 1, 1, 0,
                1, 0, 0, 1,
                1, 0, 0, 1,
                0, 1, 1, 0};

        int id = 0;
        for (int i = 0; i < image.length; i++) {
            if (image[i] != 0) {
                image[id] = id;
            } else {
                image[id] = 0;
            }
            id++;
        }


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (image[i * width + j] != 0 && image[i * width + j + 1] != 0) {
                    Statics.disjointSet.union(image, i * width + j, i * width + j + 1);
                }
                if (i < height - 1 && image[i * width + j] != 0 && image[i * width + j + width] != 0) {
                    Statics.disjointSet.union(image, i * width + j, i * width + j + width);
                }
            }
        }

        for (int i = 0; i < image.length; i++) {
            if (image[i] != 0 && !roots.contains(Statics.disjointSet.find(image, i))) {
                roots.add(Statics.disjointSet.find(image, i));
            }
        }

        // Only thing I can think of testing for

        assertEquals(3, roots.size());
        assertNotEquals(0, roots.size());
        assertNotEquals(1, roots.size());
        assertNotEquals(2, roots.size());
        assertNotEquals(4, roots.size());
    }


}