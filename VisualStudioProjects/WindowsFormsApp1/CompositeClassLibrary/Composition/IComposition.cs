﻿using CompositeClassLibrary.CompositePattern;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary
{
    interface IComposition
    {
        void Add(IComponent component);
    }
}