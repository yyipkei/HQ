package com.bridge.main;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.apache.log4j.PropertyConfigurator;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

@DisallowConcurrentExecution
public class Quartz {

	public static void main(String args[]) {
		try {
			// Configuration of log4j
			PropertyConfigurator.configure("log4j.properties");
			//PropertyConfigurator.configure("C:\\Source\\BridgeLC\\src\\main\\resources\\log4j.properties");

			/*
			 * ClassLoader loader =
			 * Thread.currentThread().getContextClassLoader(); URL url =
			 * loader.getResource("log4j.properties");
			 * PropertyConfigurator.configure(url);
			 */
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// and start it off
			scheduler.start();

			// define the job and tie it to our HelloJob class ->
			// JobBuilder.newJob()
			JobDetail jobSales = newJob(MainSales.class).withIdentity("job1",
					"group1").build();

			JobDetail jobItem = newJob(MainItem.class).withIdentity("job2",
					"group2").build();

			JobDetail jobStaff = newJob(MainStaffpurchase.class).withIdentity(
					"job3", "group3").build();

			JobDetail jobGdrtn = newJob(MainGoodsreturn.class).withIdentity(
					"job4", "group4").build();

			JobDetail jobMerge = newJob(MainMerge.class).withIdentity("job5",
					"group5").build();

			JobDetail jobGoasetting = newJob(MainGoasetting.class)
					.withIdentity("job6", "group6").build();

			JobDetail jobStock = newJob(MainStock.class).withIdentity("job7",
                    "group7").build();

			JobDetail jobDeposit = newJob(MainDeposit.class).withIdentity(
					"job8", "group8").build();

			JobDetail jobStockonhand = newJob(MainStockonhand.class).withIdentity(
					"job9", "group9").build();

            JobDetail jobVip = newJob(MainVip.class).withIdentity(
                    "job10", "group10").build();

			JobDetail jobPatch = newJob(MainPatch.class).withIdentity(
					"job11", "group11").build();

			// Trigger the job to run now, and then repe+at every 15 seconds ->
			// TriggerBuilder.newTrigger()
			Trigger trigger = newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger2 = newTrigger()
					.withIdentity("trigger2", "group2")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger3 = newTrigger()
					.withIdentity("trigger3", "group3")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger4 = newTrigger()
					.withIdentity("trigger4", "group4")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger5 = newTrigger()
					.withIdentity("trigger5", "group5")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger6 = newTrigger()
					.withIdentity("trigger6", "group6")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger7 = newTrigger()
					.withIdentity("trigger7", "group7")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger8 = newTrigger()
					.withIdentity("trigger8", "group8")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

			Trigger trigger9 = newTrigger()
					.withIdentity("trigger9", "group9")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.repeatForever()).build();

            Trigger trigger10 = newTrigger()
                    .withIdentity("trigger10", "group10")
                    .startNow()
                    .withSchedule(
                            simpleSchedule().withIntervalInSeconds(10)
                                    .repeatForever()).build();

			Trigger trigger11 = newTrigger()
					.withIdentity("trigger11", "group11")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(1000)
									.repeatForever()).build();

			//Connection Pool
			//HikariMssql.getInstance();
			HikariQracleTo.getInstance();
			HikariQracleFrom.getInstance();
			HikariRms.getInstance();
			HikariMerge.getInstance();

			// Tell quartz to schedule the job using our trigger
			//scheduler.scheduleJob(jobSales, trigger); // Sales data
			scheduler.scheduleJob(jobItem, trigger2); // item master
			scheduler.scheduleJob(jobStaff, trigger3);// staff discount
			scheduler.scheduleJob(jobGdrtn, trigger4);// Goods return
			scheduler.scheduleJob(jobMerge, trigger5);// Merge
			//scheduler.scheduleJob(jobGoasetting, trigger6);// GOA Setting
			scheduler.scheduleJob(jobStock, trigger7);// Stock
			scheduler.scheduleJob(jobDeposit, trigger8);// Deposit
			scheduler.scheduleJob(jobStockonhand, trigger9);// Onhand
            //scheduler.scheduleJob(jobVip, trigger10);// vip
			// Thread.sleep(1000 * 60 * 5); // Sleep 5 minutes
			scheduler.scheduleJob(jobPatch, trigger11);//Patch
			// scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
