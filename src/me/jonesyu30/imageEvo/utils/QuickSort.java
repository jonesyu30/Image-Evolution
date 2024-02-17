package me.jonesyu30.imageEvo.utils;

import java.util.ArrayList;
import java.util.Collections;

import me.jonesyu30.imageEvo.core.GenImage;

public class QuickSort {
	private static void swap(ArrayList<GenImage> genImages, int i, int j)
    {
		Collections.swap(genImages, i, j);
    }
 
	private static int partition(ArrayList<GenImage> genImages, int low, int high)
    {
        double pivot = genImages.get(high).getDiff();
 
        int i = (low - 1);
 
        for (int j = low; j <= high - 1; j++) {
 
            if (genImages.get(j).getDiff() < pivot) {
 
                i++;
                swap(genImages, i, j);
            }
        }
        swap(genImages, i + 1, high);
        return (i + 1);
    }
 
	public static void quickSort(ArrayList<GenImage> genImages, int low, int high)
    {
        if (low < high) {
 
            int pi = partition(genImages, low, high);
 
            quickSort(genImages, low, pi - 1);
            quickSort(genImages, pi + 1, high);
        }
    }
}
