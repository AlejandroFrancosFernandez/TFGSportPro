package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines

import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.Exercise
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.LowIntensityExercises

class LowRoutine {

    fun getRoutineForDayLow(day: Int): List<Exercise> {
        return when (day) {
            1 -> listOf(
                LowIntensityExercises.highKnees,
                LowIntensityExercises.jumpingJacks,

                LowIntensityExercises.lunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaises,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.kneePushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.sidePlank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.gluteBridge
            )
            2 -> listOf(
                LowIntensityExercises.jumpingSquats,
                LowIntensityExercises.highKnees,

                LowIntensityExercises.stairClimbing,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.elbowPlank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.kneeRaisesPlank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.waistRotation,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.singleLegGluteBridge,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.bicycleCrunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.hipRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.kneePushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise
            )
            3 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.seatedLegExtension,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.gluteBridge,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaisesOnStep,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.kneePushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralJumps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank
            )
            4 -> listOf(
                LowIntensityExercises.jumpingInPlace,
                LowIntensityExercises.lowSkipping,

                LowIntensityExercises.catLegRaises,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.sidePlank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.jumpingJacks,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.reverseLunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralSquat,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.hipRaise
            )
            5 -> listOf(
                LowIntensityExercises.highKnees,
                LowIntensityExercises.jumpingInPlace,

                LowIntensityExercises.jumpingLunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.catLegRaises,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.crossBodyCrunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.kneePushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise
            )
            6 -> listOf(
                LowIntensityExercises.jumpingJacks,
                LowIntensityExercises.highKnees,

                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.jumpingLunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.heelRaisesOnStep
            )
            7 -> listOf(
                LowIntensityExercises.jumpingSquats,
                LowIntensityExercises.jumpingJacks,

                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.jumpingSquats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.sidePlank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lyingLegRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.hipRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps
            )
            8 -> listOf(
                LowIntensityExercises.lowSkipping,
                LowIntensityExercises.jumpingSquats,

                LowIntensityExercises.reverseLunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.shortCrunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lyingLegRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.gluteBridge,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralJumps
            )
            9 -> listOf(
                LowIntensityExercises.jumpingInPlace,
                LowIntensityExercises.lowSkipping,

                LowIntensityExercises.crunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.jumpingSquats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lyingLegRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.hipRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.stepUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.shortCrunches
            )
            10 -> listOf(
                LowIntensityExercises.jumpingSquats,
                LowIntensityExercises.jumpingInPlace,

                LowIntensityExercises.reverseLunges,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.pushUps,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.squats,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.plank,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.shortCrunches,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralLegRaise,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.gluteBridge,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.superman,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.russianTwists,
                LowIntensityExercises.BREAK,
                LowIntensityExercises.lateralJumps
            )
            else -> emptyList()
        }
    }
}