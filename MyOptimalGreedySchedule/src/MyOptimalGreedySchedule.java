import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/*
For this problem, use the same data set as in the previous problem. Your task
now is to run the greedy algorithm that schedules jobs (optimally) in
decreasing order of the ratio (weight/length). In this algorithm, it does not
matter how you break ties. You should report the sum of weighted completion
times of the resulting schedule --- a positive integer --- in the box below. 
*/
import java.util.ArrayList;
import java.util.Collections;

//Class to store attributes of jobs
class MyJob implements Comparable<MyJob> {
	Integer weight;
	Integer length;
	Float score;

	@Override
	public int compareTo(MyJob otherJob) {
		return otherJob.score.compareTo(this.score);
	}
}

public class MyOptimalGreedySchedule {
	// Array list to hold jobs
	private static ArrayList<MyJob> jobArray;
	
	private static Integer numJobs;
	
	// Return the score for the job that will be used for greedy scheduling
	private static Float calculateScore(Integer weight, Integer length) {
		return ((float)weight / (float)length);
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
