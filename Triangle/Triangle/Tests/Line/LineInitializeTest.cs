using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Triangle.Tests.LineTest
{
    [TestClass]
    public class LineInitializeTest
    {
        [TestMethod]
        public void WithValidData_ExpectCorrectResult()
        {
            new Line(1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void WithInValidData_ExpectCorrectResult()
        {
            new Line(-1);
        }
    }
}
