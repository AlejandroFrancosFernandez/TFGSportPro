import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.Exercise
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.HighIntensityExercises
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.LowIntensityExercises

class MediumRoutine {

    fun getRoutineForDayMedium(day: Int): List<Exercise> {
        return when (day) {
            1 -> listOf(
                LowIntensityExercises.highKnees,
                LowIntensityExercises.jumpingJacks,

                HighIntensityExercises.controlledPushupsSlowHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.jumpingLungesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaises,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman
            )
            2 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.clapPushUpsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.vCrunchesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.singleLegPlankHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.gluteBridge
            )
            3 -> listOf(
                LowIntensityExercises.plyoJacks,
                HighIntensityExercises.boxing,

                HighIntensityExercises.jumpingJacksHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.jumpingSquats,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.clapPushUpsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaises,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.sumoSquatHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps
            )
            4 -> listOf(
                HighIntensityExercises.dynamicSquatsHigh,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.plyoJacksHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.controlledPushupsSlowHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.lateralJumpsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaisesOnStep,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.jumpingLungesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists
            )
            5 -> listOf(
                LowIntensityExercises.plyoJacks,
                HighIntensityExercises.boxing,

                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaises,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.lungesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.highKneesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.boxing,////////
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats
            )
            6 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.vCrunchesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.singleLegPlankHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.gluteBridge
            )
            7 -> listOf(
                LowIntensityExercises.plyoJacks,
                HighIntensityExercises.boxing,

                HighIntensityExercises.jumpingJacksHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.jumpingSquats,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.clapPushUpsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaises,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.sumoSquatHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.plankWithShoulderTapHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps
            )
            8 -> listOf(
                HighIntensityExercises.dynamicSquatsHigh,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.plyoJacksHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.controlledPushupsSlowHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.climbersHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.lateralJumpsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaisesOnStep,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.jumpingLungesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists
            )
            9 -> listOf(
                LowIntensityExercises.plyoJacks,
                HighIntensityExercises.boxing,

                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaises,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.lungesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.highKneesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.boxing,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats
            )
            10 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.vCrunchesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.singleLegPlankHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.basicCrunches,
                LowIntensityExercises.BREAK,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.gluteBridge
            )
            else -> listOf()
        }
    }
}
