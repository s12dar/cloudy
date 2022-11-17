package com.example.cloudy.utils

import com.google.common.truth.Truth.assertThat


infix fun Any?.`should be`(expected: Any?) {
    assertThat(this).isEqualTo(expected)
}