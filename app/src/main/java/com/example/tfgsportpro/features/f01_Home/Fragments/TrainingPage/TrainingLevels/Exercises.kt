package com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.TrainingLevels

import com.example.tfgsportpro.R
import kotlin.math.E

class Exercise(
    val nombreResId: Int,
    val duracion: Int = 30,
)

object Exercises {
    val highKnees = Exercise(R.string.high_Knees, 30)
    val jumpingJacks = Exercise(R.string.jumping_Jacks, 30)
    val lunges = Exercise(R.string.lunges, 30)
    val heelRaises = Exercise(R.string.heel_Raises, 30)
    val kneePushUps = Exercise(R.string.knee_Push_Ups, 30)
    val russianTwists = Exercise(R.string.russian_Twists, 30)
    val sidePlank = Exercise(R.string.side_Plank, 30)
    val crunches = Exercise(R.string.crunches, 30)
    val superman = Exercise(R.string.superman, 30)
    val squats = Exercise(R.string.squats, 30)
    val stepUps = Exercise(R.string.step_Ups, 30)
    val gluteBridge = Exercise(R.string.glute_Bridge, 30)
    val jumpingSquats = Exercise(R.string.jumping_Squats, 30)
    val stairClimbing = Exercise(R.string.stair_Climbing, 30)
    val elbowPlank = Exercise(R.string.elbow_Plank, 30)
    val kneeRaisesPlank = Exercise(R.string.knee_Raises_Plank, 30)
    val waistRotation = Exercise(R.string.waist_Rotation, 30)
    val singleLegGluteBridge = Exercise(R.string.single_Leg_Glute_Bridge, 30)
    val bicycleCrunches = Exercise(R.string.bicycle_Crunches, 30)
    val hipRaise = Exercise(R.string.hip_Raise, 30)
    val lateralLegRaise = Exercise(R.string.lateral_Leg_Raise, 30)
    val lowSkipping = Exercise(R.string.low_Skipping, 30)
    val seatedLegExtension = Exercise(R.string.seated_Leg_Extension, 30)
    val heelRaisesOnStep = Exercise(R.string.heel_Raises_On_Step, 30)
    val lateralJumps = Exercise(R.string.lateral_Jumps, 30)
    val lateralLunges = Exercise(R.string.lateral_Lunges, 30)
    val plank = Exercise(R.string.plank, 30)
    val jumpingInPlace = Exercise(R.string.jumping_In_Place, 30)
    val catLegRaises = Exercise(R.string.cat_Leg_Raises, 30)
    val reverseLunges = Exercise(R.string.reverse_Lunges, 30)
    val pushUps = Exercise(R.string.push_Ups, 30)
    val lateralSquat = Exercise(R.string.lateral_Squat, 30)
    val jumpingLunges = Exercise(R.string.jumping_Lunges, 30)
    val crossBodyCrunches = Exercise(R.string.cross_Body_crunches, 30)
    val lyingLegRaise = Exercise(R.string.lying_Leg_Raise, 30)
    val shortCrunches = Exercise(R.string.short_Crunches, 30)

    fun getRoutineForDayLow(day: Int): List<Exercise> {
        return when (day) {
            1 -> listOf(highKnees, jumpingJacks,
                lunges, heelRaises, kneePushUps, russianTwists, sidePlank, crunches, superman, squats, stepUps, gluteBridge
            )
            2 -> listOf(jumpingSquats, highKnees,
                stairClimbing, elbowPlank, kneeRaisesPlank, waistRotation, singleLegGluteBridge, bicycleCrunches, squats, hipRaise, kneePushUps, lateralLegRaise
            )
            3 -> listOf(lowSkipping, jumpingSquats,
                crunches, seatedLegExtension, gluteBridge, heelRaisesOnStep, kneePushUps, superman, squats, lateralJumps, lateralLunges, plank
            )
            4 -> listOf(jumpingInPlace, lowSkipping,
                catLegRaises, sidePlank, russianTwists, jumpingJacks, crunches, squats, reverseLunges, pushUps, lateralSquat, hipRaise
            )
            5 -> listOf(highKnees, jumpingInPlace,
                jumpingLunges, plank, superman, catLegRaises, crossBodyCrunches, squats, stepUps, kneePushUps, crunches, lateralLegRaise
            )
            6 -> listOf(jumpingJacks, highKnees,
                superman, crunches, stepUps, pushUps, lateralLegRaise, squats, plank, jumpingLunges, crunches, heelRaisesOnStep
            )
            7 -> listOf(jumpingSquats, jumpingJacks,
                crunches, pushUps, jumpingSquats, sidePlank,lunges, lyingLegRaise, superman, hipRaise, stepUps, pushUps
            )
            8 -> listOf(lowSkipping, jumpingSquats,
                reverseLunges, pushUps, squats, plank, shortCrunches, lyingLegRaise, gluteBridge, superman, russianTwists, lateralJumps
            )
            9 -> listOf(jumpingInPlace, lowSkipping,
                crunches, pushUps, jumpingSquats, plank, lunges, lyingLegRaise, superman, hipRaise, stepUps, shortCrunches
            )
            10 -> listOf(jumpingSquats, jumpingInPlace,
                reverseLunges, pushUps, squats, plank, shortCrunches, lateralLegRaise, gluteBridge, superman, russianTwists, lateralJumps
            )
            else -> emptyList()
        }
    }
}

object ExercisesHigh {
    val highKneesHigh = Exercise(R.string.high_Knees, 35)
    val highSkippingHigh = Exercise(R.string.high_Skipping, 35)
    val jumpingInPlaceHigh = Exercise(R.string.jumping_In_Place, 35)
    val intenseJumpingJacksHigh = Exercise(R.string.intense_Jumping_Jacks, 35)
    val intenseJumpingSquatsHigh = Exercise(R.string.intense_Jumping_Squats, 35)
    val jumpingJacksHigh = Exercise(R.string.jumping_Jacks, 35)
    val dynamicSquatsHigh = Exercise(R.string.dynamic_Squats, 35)
    val jumpingSquatsHigh = Exercise(R.string.jumping_Squats, 35)
    val controlledPushupsSlowHigh = Exercise(R.string.controlled_Pushups_Slow, 35)
    val bicycleCrunchesHigh = Exercise(R.string.bicycle_Crunches, 35)
    val jumpingLungesHigh = Exercise(R.string.jumping_Lunges, 35)
    val sprintInPlaceHigh = Exercise(R.string.sprint_In_Place, 35)
    val burpeesHigh = Exercise(R.string.burpees, 35)
    val climbersHigh = Exercise(R.string.climbers, 35)
    val plankWithShoulderTapHigh = Exercise(R.string.plank_With_Shoulder_Tap, 35)
    val weightedRussianTwistsHigh = Exercise(R.string.weighted_Russian_Twists, 35)
    val gluteBridgeHigh = Exercise(R.string.glute_Bridge, 35)
    val clapPushUpsHigh = Exercise(R.string.clap_Push_Ups, 35)
    val lungesHigh = Exercise(R.string.lunges, 35)
    val vCrunchesHigh = Exercise(R.string.v_Crunches, 35)
    val singleLegPlankHigh = Exercise(R.string.single_Leg_Plank, 35)
    val crossoverLungesHigh = Exercise(R.string.crossover_Lunges, 35)
    val plankLegRaisesHigh = Exercise(R.string.plank_Leg_Raises, 35)
    val lateralPlankWalkHigh = Exercise(R.string.lateral_Plank_Walk, 35)
    val explosivePushUpsHigh = Exercise(R.string.explosive_Push_Ups, 35)
    val lLegRaisesHigh = Exercise(R.string.l_Leg_Raises, 35)
    val supermanHigh = Exercise(R.string.superman, 35)
    val heelToGluteHigh = Exercise(R.string.heel_To_Glute, 35)
    val squatWithPauseHigh = Exercise(R.string.squat_with_pause, 35)
    val shortCrunchesHigh = Exercise(R.string.short_Crunches, 35)
    val heelTapCrunchesHigh = Exercise(R.string.heel_Tap_Crunches, 35)
    val isometricSquatHigh = Exercise(R.string.isometric_squat, 35)
    val singleLegSquatsHigh = Exercise(R.string.single_Leg_Squats, 35)
    val crossBodyCrunchesHigh = Exercise(R.string.cross_Body_crunches, 35)
    val plankAlternateKneeTapsHigh = Exercise(R.string.plank_Alternate_Knee_Taps, 35)
    val fastLungesHigh = Exercise(R.string.fast_Lunges, 35)
    val lateralJumpsHigh = Exercise(R.string.lateral_Jumps, 35)
    val sumoSquatHigh = Exercise(R.string.sumo_squat, 35)
    val bulgarianSplitSquatHigh = Exercise(R.string.bulgarian_split_squat, 35)
    val crunches90DegreesHigh = Exercise(R.string.crunches_90_Degrees, 35)
    val lateralLungesHigh = Exercise(R.string.lateral_Lunges, 35)

    fun getRoutineForDayHigh(day: Int): List<Exercise> {
        return when (day) {
            1 -> listOf(jumpingInPlaceHigh, intenseJumpingJacksHigh,
                controlledPushupsSlowHigh, bicycleCrunchesHigh, jumpingLungesHigh, sprintInPlaceHigh, burpeesHigh, climbersHigh, plankWithShoulderTapHigh, intenseJumpingSquatsHigh, weightedRussianTwistsHigh, gluteBridgeHigh
            )
            2 -> listOf(jumpingJacksHigh, jumpingSquatsHigh,
                clapPushUpsHigh, lungesHigh, vCrunchesHigh, burpeesHigh, singleLegPlankHigh, weightedRussianTwistsHigh, plankWithShoulderTapHigh, climbersHigh, heelToGluteHigh
            )
            3 -> listOf(highKneesHigh, intenseJumpingJacksHigh,
                explosivePushUpsHigh, shortCrunchesHigh, sumoSquatHigh, bulgarianSplitSquatHigh, plankWithShoulderTapHigh, supermanHigh, weightedRussianTwistsHigh, lateralJumpsHigh, heelTapCrunchesHigh, plankAlternateKneeTapsHigh
            )
            4 -> listOf(jumpingJacksHigh, dynamicSquatsHigh,
                clapPushUpsHigh, vCrunchesHigh, singleLegSquatsHigh, plankLegRaisesHigh, burpeesHigh, crossoverLungesHigh, singleLegPlankHigh, climbersHigh, singleLegSquatsHigh, plankWithShoulderTapHigh
            )
            5 -> listOf(highKneesHigh, jumpingJacksHigh,
                controlledPushupsSlowHigh, lateralLungesHigh, bicycleCrunchesHigh, sprintInPlaceHigh, crunches90DegreesHigh, burpeesHigh, singleLegSquatsHigh, lateralPlankWalkHigh, singleLegPlankHigh
            )
            6 -> listOf(highKneesHigh, jumpingJacksHigh,
                explosivePushUpsHigh, burpeesHigh, plankWithShoulderTapHigh, fastLungesHigh, lLegRaisesHigh, supermanHigh, shortCrunchesHigh, heelToGluteHigh, heelTapCrunchesHigh, isometricSquatHigh
            )
            7 -> listOf(highSkippingHigh, jumpingInPlaceHigh,
                controlledPushupsSlowHigh, fastLungesHigh, vCrunchesHigh, plankLegRaisesHigh, climbersHigh, shortCrunchesHigh, sprintInPlaceHigh, plankAlternateKneeTapsHigh, singleLegSquatsHigh, singleLegPlankHigh
            )
            8 -> listOf(highKneesHigh, jumpingJacksHigh,
                controlledPushupsSlowHigh, singleLegSquatsHigh, jumpingLungesHigh, bicycleCrunchesHigh, crossBodyCrunchesHigh, supermanHigh, sprintInPlaceHigh, burpeesHigh, lateralPlankWalkHigh, plankAlternateKneeTapsHigh
            )
            9 -> listOf(jumpingJacksHigh, highSkippingHigh,
                explosivePushUpsHigh, squatWithPauseHigh, sprintInPlaceHigh, plankWithShoulderTapHigh, fastLungesHigh, shortCrunchesHigh, vCrunchesHigh, climbersHigh, heelToGluteHigh, lateralPlankWalkHigh
            )
            10 -> listOf(highKneesHigh, jumpingSquatsHigh,
                clapPushUpsHigh, squatWithPauseHigh, lLegRaisesHigh, fastLungesHigh, crunches90DegreesHigh, lateralPlankWalkHigh, burpeesHigh, sprintInPlaceHigh, fastLungesHigh, plankAlternateKneeTapsHigh
            )

            else -> emptyList()
        }
    }
}


