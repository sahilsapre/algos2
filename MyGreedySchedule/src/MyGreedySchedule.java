import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
Algorithms Part 2 Assignment 1 Question 1
In this programming problem and the next you'll code up the greedy algorithms
from lecture for minimizing the weighted sum of completion times.. Download 
the text file here. This file describes a set of jobs with positive and 
integral weights and lengths. It has the format

[number_of_jobs]
[job_1_weight] [job_1_length]
[job_2_weight] [job_2_length]
...
For example, the third line of the file is "74 59", indicating that the second
job has weight 74 and length 59. You should NOT assume that edge weights or 
lengths are distinct.

Your task in this problem is to run the greedy algorithm that schedules jobs in
decreasing order of the difference (weight - length). Recall from lecture that
this algorithm is not always optimal. IMPORTANT: if two jobs have equal
difference (weight - length), you should schedule the job with higher weight
first. Beware: if you break ties in a different way, you are likely to get the
wrong answer. You should report the sum of weighted completion times of the 
resulting schedule --- a positive integer --- in the box below.

ADVICE: If you get the wrong answer, try out some small test cases to debug
your algorithm (and post your test cases to the discussion forum)!
*/

// Class to store attributes of jobs
class MyJob implements Comparable<MyJob> {
	Integer weight;
	Integer length;
	Integer score;

	@Override
	public int compareTo(MyJob otherJob) {
		if (otherJob.score.compareTo(this.score) == 0)
			return otherJob.weight.compareTo(this.weight);
		return otherJob.score.compareTo(this.score);
	}
}

public class MyGreedySchedule {
	// Array list to hold jobs
	private static ArrayList<MyJob> jobArray;
	
	private static Integer numJobs;
	
	// Return the score for the job that will be used for greedy scheduling
	private static Integer calculateScore(Integer weight, Integer length) {
		return (weight - length);
	}
	
	// Read from the input file and store the jobs
	private static void readFromFile() throws IOException {
		FileInputStream fstream = new FileInputStream("jobs.txt");

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		// We know that the first line is the number of jobs
		String line = br.readLine();
		if (line != null) 
		{
			numJobs = Integer.valueOf(line);
		} else 
		{
			numJobs = 0;
		}

		if (numJobs == 0) {
			System.out.println("No jobs to process or wrong format of file");
			br.close();
			return;
		}

		// Array list to hold jobs
		jobArray = new ArrayList<MyJob>();

		// Read in jobs from file
		for (Integer i = 0; i < numJobs; i++) {
			line = br.readLine();
			String[] strArray = line.split("\\s+");
			MyJob job = new MyJob();
			job.weight = Integer.parseInt(strArray[0]);
			job.length = Integer.parseInt(strArray[1]);
			job.score = calculateScore(job.weight, job.length);

			jobArray.add(job);
		}
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		readFromFile();
		
		// Sort the jobs according to the greedy criterion
		Collections.sort(jobArray);
		
		// Now schedule them
		Integer compTime = 0;
		Long weightedCompTime = (long) 0;
		for (Integer i = 0; i < numJobs; i++)
		{
			compTime += jobArray.get(i).length;
			weightedCompTime += (compTime * jobArray.get(i).weight);
		}
		System.out.println("Weighted Completion Time: " + weightedCompTime);
	}
}