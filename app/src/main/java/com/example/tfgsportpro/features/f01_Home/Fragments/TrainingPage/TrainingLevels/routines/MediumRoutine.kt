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
                LowIntensityExercises.crunches,
                HighIntensityExercises.jumpingLungesHigh,
                LowIntensityExercises.stepUps,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.heelRaises,
                HighIntensityExercises.climbersHigh,
                LowIntensityExercises.squats,
                HighIntensityExercises.plankWithShoulderTapHigh,
                LowIntensityExercises.superman
            )
            2 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.clapPushUpsHigh,
                LowIntensityExercises.pushUps,
                HighIntensityExercises.vCrunchesHigh,
                LowIntensityExercises.waistRotation,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.plank,
                HighIntensityExercises.singleLegPlankHigh,
                LowIntensityExercises.crunches,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.gluteBridge
            )
            3 -> listOf(
                LowIntensityExercises.jumpingInPlace,
                HighIntensityExercises.intenseJumpingJacksHigh,

                HighIntensityExercises.jumpingJacksHigh,
                LowIntensityExercises.jumpingSquats,
                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.lateralLegRaise,
                HighIntensityExercises.clapPushUpsHigh,
                LowIntensityExercises.heelRaises,
                HighIntensityExercises.sumoSquatHigh,
                LowIntensityExercises.crunches,
                HighIntensityExercises.plankWithShoulderTapHigh,
                LowIntensityExercises.stepUps
            )
            4 -> listOf(
                HighIntensityExercises.dynamicSquatsHigh,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.jumpingInPlaceHigh,
                LowIntensityExercises.pushUps,
                HighIntensityExercises.controlledPushupsSlowHigh,
                LowIntensityExercises.squats,
                HighIntensityExercises.climbersHigh,
                LowIntensityExercises.plank,
                HighIntensityExercises.lateralJumpsHigh,
                LowIntensityExercises.heelRaisesOnStep,
                HighIntensityExercises.jumpingLungesHigh,
                LowIntensityExercises.russianTwists
            )
            5 -> listOf(
                LowIntensityExercises.jumpingInPlace,
                HighIntensityExercises.intenseJumpingJacksHigh,

                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.heelRaises,
                HighIntensityExercises.lungesHigh,
                LowIntensityExercises.lateralLegRaise,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.stepUps,
                HighIntensityExercises.highKneesHigh,
                LowIntensityExercises.crunches,
                HighIntensityExercises.intenseJumpingJacksHigh,
                LowIntensityExercises.squats
            )
            6 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.pushUps,
                HighIntensityExercises.vCrunchesHigh,
                LowIntensityExercises.waistRotation,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.plank,
                HighIntensityExercises.singleLegPlankHigh,
                LowIntensityExercises.crunches,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.gluteBridge
            )
            7 -> listOf(
                LowIntensityExercises.jumpingInPlace,
                HighIntensityExercises.intenseJumpingJacksHigh,

                HighIntensityExercises.jumpingJacksHigh,
                LowIntensityExercises.jumpingSquats,
                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.lateralLegRaise,
                HighIntensityExercises.clapPushUpsHigh,
                LowIntensityExercises.heelRaises,
                HighIntensityExercises.sumoSquatHigh,
                LowIntensityExercises.crunches,
                HighIntensityExercises.plankWithShoulderTapHigh,
                LowIntensityExercises.stepUps
            )
            8 -> listOf(
                HighIntensityExercises.dynamicSquatsHigh,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.jumpingInPlaceHigh,
                LowIntensityExercises.pushUps,
                HighIntensityExercises.controlledPushupsSlowHigh,
                LowIntensityExercises.squats,
                HighIntensityExercises.climbersHigh,
                LowIntensityExercises.plank,
                HighIntensityExercises.lateralJumpsHigh,
                LowIntensityExercises.heelRaisesOnStep,
                HighIntensityExercises.jumpingLungesHigh,
                LowIntensityExercises.russianTwists
            )
            9 -> listOf(
                LowIntensityExercises.jumpingInPlace,
                HighIntensityExercises.intenseJumpingJacksHigh,

                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.heelRaises,
                HighIntensityExercises.lungesHigh,
                LowIntensityExercises.lateralLegRaise,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.stepUps,
                HighIntensityExercises.highKneesHigh,
                LowIntensityExercises.crunches,
                HighIntensityExercises.intenseJumpingJacksHigh,
                LowIntensityExercises.squats
            )
            10 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                HighIntensityExercises.squatWithPauseHigh,
                LowIntensityExercises.pushUps,
                HighIntensityExercises.vCrunchesHigh,
                LowIntensityExercises.waistRotation,
                HighIntensityExercises.burpeesHigh,
                LowIntensityExercises.plank,
                HighIntensityExercises.singleLegPlankHigh,
                LowIntensityExercises.crunches,
                HighIntensityExercises.weightedRussianTwistsHigh,
                LowIntensityExercises.gluteBridge
            )
            else -> listOf()
        }
    }
}
