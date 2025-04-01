package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines

import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.Exercise
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.HighIntensityExercises

class HighRoutine {

    fun getRoutineForDayHigh(day: Int): List<Exercise> {
        return when (day) {
            1 -> listOf(
                HighIntensityExercises.highKneesHigh,
                HighIntensityExercises.pushUpToeTouchHigh,

                HighIntensityExercises.controlledPushupsSlowHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.bicycleCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.jumpingLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.sprintInPlaceHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.intenseJumpingSquatsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.gluteBridgeHigh
            )
            2 -> listOf(
                HighIntensityExercises.pushUpToeTouchHigh,
                HighIntensityExercises.plyoJacksHigh,

                HighIntensityExercises.clapPushUpsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.vCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegPlankHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.heelToGluteHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lateralPlankWalkHigh
            )
            3 -> listOf(
                HighIntensityExercises.plyoJacksHigh,
                HighIntensityExercises.boxing,

                HighIntensityExercises.explosivePushUpsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.shortCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.sumoSquatHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.bulgarianSplitSquatHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.supermanHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lateralJumpsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.heelTapCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankAlternateKneeTapsHigh
            )
            4 -> listOf(
                HighIntensityExercises.boxing,
                HighIntensityExercises.bearCrawl,

                HighIntensityExercises.clapPushUpsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.vCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegSquatsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankLegRaisesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.crossoverLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegPlankHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegSquatsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh
            )
            5 -> listOf(
                HighIntensityExercises.bearCrawl,
                HighIntensityExercises.jumpingJacksHigh,

                HighIntensityExercises.controlledPushupsSlowHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lateralLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.bicycleCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.sprintInPlaceHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.crunches90DegreesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegSquatsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lateralPlankWalkHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegPlankHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankAlternateKneeTapsHigh
            )
            6 -> listOf(
                HighIntensityExercises.jumpingJacksHigh,
                HighIntensityExercises.dynamicSquatsHigh,

                HighIntensityExercises.explosivePushUpsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.fastLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lLegRaisesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.supermanHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.shortCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.heelToGluteHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.heelTapCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.isometricSquatHigh
            )
            7 -> listOf(
                HighIntensityExercises.dynamicSquatsHigh,
                HighIntensityExercises.highKneesHigh,

                HighIntensityExercises.controlledPushupsSlowHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.fastLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.vCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankLegRaisesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.shortCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.sprintInPlaceHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankAlternateKneeTapsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegSquatsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegPlankHigh
            )
            8 -> listOf(
                HighIntensityExercises.highKneesHigh,
                HighIntensityExercises.pushUpToeTouchHigh,

                HighIntensityExercises.controlledPushupsSlowHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.singleLegSquatsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.jumpingLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.bicycleCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.crossBodyCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.supermanHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.sprintInPlaceHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lateralPlankWalkHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankAlternateKneeTapsHigh
            )
            9 -> listOf(
                HighIntensityExercises.pushUpToeTouchHigh,
                HighIntensityExercises.plyoJacksHigh,

                HighIntensityExercises.explosivePushUpsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.squatWithPauseHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.sprintInPlaceHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.fastLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.shortCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.vCrunchesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.heelToGluteHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lateralPlankWalkHigh
            )
            10 -> listOf(
                HighIntensityExercises.plyoJacksHigh,
                HighIntensityExercises.boxing,

                HighIntensityExercises.clapPushUpsHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.squatWithPauseHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lLegRaisesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.fastLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.crunches90DegreesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.lateralPlankWalkHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.sprintInPlaceHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.fastLungesHigh,
                HighIntensityExercises.BREAK,
                HighIntensityExercises.plankAlternateKneeTapsHigh
            )
            11 -> listOf(
                HighIntensityExercises.boxing,
                HighIntensityExercises.bearCrawl,


            )
            12 -> listOf(
                HighIntensityExercises.bearCrawl,
                HighIntensityExercises.jumpingJacksHigh,


            )
            13 -> listOf(
                HighIntensityExercises.jumpingJacksHigh,
                HighIntensityExercises.dynamicSquatsHigh,


            )
            14 -> listOf(
                HighIntensityExercises.bearCrawl,
                HighIntensityExercises.boxing,


            )
            15 -> listOf(
                HighIntensityExercises.plyoJacksHigh,
                HighIntensityExercises.pushUpToeTouchHigh,


            )
            else -> emptyList()
        }
    }
}