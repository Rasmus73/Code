using System;
using System.Drawing;

namespace CompositeClassLibrary.CompositePattern
{
    public class ComponentText : IComponent
    {
        long YCoord { get; set; }

        public string Name { get; set; }

        public ComponentText(string name)
        {
            Name = name;
        }

        public void Add(IComponent component)
        {
            throw new NotImplementedException();
        }

        public void Draw(Graphics graphics)
        {
            System.Drawing.Font font = new Font("TimesNewRoman", 8);
            graphics.DrawString(Name, font, new SolidBrush(Color.Black), 1, 20);
        }

    }
}